
package com.SoftwareFactoryCustomer.controller.customerAdmin;


import com.SoftwareFactoryCustomer.comparator.CaseByStatusAndDateComparator;
import com.SoftwareFactoryCustomer.comparator.MessageByDateComparator;
import com.SoftwareFactoryCustomer.comparator.ProjectByDateComparator;
import com.SoftwareFactoryCustomer.constant.MessageEnum;
import com.SoftwareFactoryCustomer.constant.StatusEnum;
import com.SoftwareFactoryCustomer.model.*;
import com.SoftwareFactoryCustomer.service.*;
import com.SoftwareFactoryCustomer.util.SaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("roles")
public class CustomerCabinetController {

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CaseService caseService;

    @Autowired
    private ManagerInfoService managerInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getCustomerCabinet(HttpSession httpSession) {

        System.out.println("cabinet");

        ModelAndView customerCabinet = new ModelAndView("customerAdminViews/customerCabinet");

        Set<Project> projectsToShow = addGeneralDataToMAVAndReturnProjects(customerCabinet, httpSession);
        ArrayList<Case> casesToShow = new ArrayList<>();

        if (projectsToShow != null) {

            // GET CASES FROM PROJECT
            for (Project project : projectsToShow) {
                getCasesFromProject(project, casesToShow);
            }
        }

        // SORT CASE
        casesToShow.sort(new CaseByStatusAndDateComparator());

        //PUT OBJECTS TO MODEL
        customerCabinet.addObject("currentProjectCasesName", "All Cases");
        customerCabinet.addObject("cases", casesToShow);

        return customerCabinet;
    }

    @RequestMapping(value = "/newCase", method = RequestMethod.GET)
    public ModelAndView newCase(HttpSession httpSession) {

        ModelAndView customerCabinet = new ModelAndView("customerAdminViews/customerCase");

        Long userId = (Long) httpSession.getAttribute("UserId");
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);

        Set<Project> projectsToShow = customerInfo.getProjects();

        List<Project> sortedProjectListToShow = new ArrayList<>(projectsToShow);
        sortedProjectListToShow.sort(new ProjectByDateComparator());

        //PUT OBJECTS TO MODEL
        customerCabinet.addObject("customerName", customerInfo.getName());
        customerCabinet.addObject("projects", sortedProjectListToShow);

        return customerCabinet;
    }

    @RequestMapping(value = "/createCase", method = RequestMethod.POST)
    public ModelAndView createCase(HttpSession httpSession, @RequestParam("projectName") String projectName,
                                   @RequestParam("caseName") String caseName,
                                   @RequestParam("message") String message, @RequestParam("language") String language,
                                   @RequestParam("fileCase[]") MultipartFile[] files,
                                   @RequestParam(value = "emergency", required = false) boolean emergency) {

        System.out.print(projectName + " " + " " + caseName + " " + message + " " + " " + language);


        Long userId = (Long) httpSession.getAttribute("UserId");
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);

        Set<Project> projects = customerInfo.getProjects();

        Project project = null;
        if (projects != null) {
            Iterator<Project> itr = projects.iterator();
            while (itr.hasNext()) {
                project = itr.next();
                if (project.getProjectName().equals(projectName)) {
                    break;
                }
            }
        }
        Case newCase = new Case();
        newCase.setProject(project);
        newCase.setProjectTitle(caseName);
        newCase.setStatus(StatusEnum.OPEN.toString());
        Date date = new Date();
        newCase.setCreationDate(date);
        newCase.setUserManagerId(0L); // <======MUST BE MANAGER ID
        newCase.setLanguage(language);
        newCase.setEmergency(emergency);

        Set<Case> caseSet = project.getCases();
        caseSet.add(newCase);
        project.setCases(caseSet);
        projectService.updateProject(project);
        Case caseCreated = caseService.getCaseById(newCase.getId());
        Set<Message> messages = caseCreated.getMessages();
        Message caseMessage = new Message();
        caseMessage.setaCase(caseCreated);
        User us = userService.findById(userId);
        caseMessage.setUser(us);
        caseMessage.setMessageTime(date);
        caseMessage.setMessageText(message);
        caseMessage.setMessageLinks(new HashSet<>());

        caseMessage.setIsRead(MessageEnum.NOTREAD.toString());

        messages.add(caseMessage);
        messageService.addNewMessage(caseMessage);
        caseCreated.setMessages(messages);
        caseService.updateCase(caseCreated);


        SaveFile saveFile = new SaveFile(files);
        saveFile.saveMessageFilesToMessage(caseMessage);
        messageService.updateMessage(caseMessage);

        pushNotificationService.pushNotificationToGCM(message, MessageEnum.CASE.toString() + " :: new Case " + newCase.getProjectTitle(), new Date());

        ModelAndView modelAndView = new ModelAndView("redirect:/list");
        return modelAndView;
    }


    @RequestMapping(value = "/case/{id}/print_message", method = RequestMethod.POST)
    public ModelAndView casePrintMessageController(@PathVariable Long id, @RequestParam("message") String messageText,
                                                   @RequestParam("file[]") MultipartFile[] files, HttpSession httpSession,
                                                   @RequestParam(value = "emergency", required = false) boolean emergency) {

        // GET
        Case aCase = caseService.getCaseById(id);
        Long userId = (Long) httpSession.getAttribute("UserId");
        User currentUser = userService.findById(userId);

        aCase.setEmergency(emergency);

        // CREATE MESSAGE
        Message message = new Message();
        message.setaCase(aCase);
        message.setUser(currentUser);
        message.setMessageTime(new Date());
        message.setMessageText(messageText);
        message.setIsRead(MessageEnum.NOTREAD.toString());
        message.setMessageLinks(new HashSet<>());
        messageService.addNewMessage(message);


        // SAVE MESSAGE TO CASE
        Set<Message> messages = aCase.getMessages();
        messages.add(message);
        aCase.setMessages(messages);


        //SAVE FILE

        SaveFile saveFile = new SaveFile(files);
        saveFile.saveMessageFilesToMessage(message);
        messageService.updateMessage(message);


        caseService.updateCase(aCase);

        // REDIRECT TO CHAT
        String redirectLink = "redirect:/cabinet/case/" + id;

        pushNotificationService.pushNotificationToGCM(messageText, MessageEnum.MESSAGE.toString() + " :: Case " + aCase.getProjectTitle(), new Date());

        return new ModelAndView(redirectLink);
    }

    @RequestMapping(value = "/case/{id}/close_case", method = RequestMethod.POST)
    public ModelAndView caseCloseController(@PathVariable Long id) {

        Case caseToClose = caseService.getCaseById(id);

        Long userId = caseToClose.getUserManagerId();

        caseToClose.setStatus(StatusEnum.CLOSE.toString());
        caseService.updateCase(caseToClose);


        pushNotificationService.pushNotificationToGCM(userId.toString(), MessageEnum.CLOSE_CASE.toString() + " :: " + caseToClose.getProjectTitle(), new Date());

        return new ModelAndView("redirect:/cabinet/");
    }


    @RequestMapping(value = "/case/{id}", method = RequestMethod.GET)
    public ModelAndView caseChatController(@PathVariable Long id, HttpSession httpSession) {

        ModelAndView caseChat = new ModelAndView("customerAdminViews/customerChat");

        addGeneralDataToMAVAndReturnProjects(caseChat, httpSession);


        // GET MESSAGE FROM CASE BY ID
        Case aCase = caseService.getCaseById(id);
        Set<Message> messagesUnsorted = aCase.getMessages();
        List<Message> messagesSorted = new ArrayList<>(messagesUnsorted);

        MessageByDateComparator messageByDateComparator = new MessageByDateComparator();

        // SORT BY DATE
        messagesSorted.sort(messageByDateComparator);

        //PUT OBJECTS TO MODEL
        caseChat.addObject("messagesSorted", messagesSorted);
        caseChat.addObject("case", aCase);
        caseChat.addObject("managerInfo", managerInfoService.getManagerInfoById(aCase.getUserManagerId()));
        return caseChat;
    }

    @RequestMapping(value = "/case/{id}/answer", method = RequestMethod.GET)
    public ModelAndView caseAnswerController(@PathVariable Long id) {

        ModelAndView caseAnswerChat = new ModelAndView("customerAdminViews/customerChatAnswer");

        Case aCase = caseService.getCaseById(id);
        Set<Message> messages = aCase.getMessages();

        caseAnswerChat.addObject("caseId", id);
        caseAnswerChat.addObject("case", aCase);
        caseAnswerChat.addObject("messages", messages);
        caseAnswerChat.addObject("customerName", aCase.getProject().getCustomerInfo().getName());

        return caseAnswerChat;
    }

    //ADD GENERAL OBJECT THAT PERSIST IN ALL JSP
    private Set<Project> addGeneralDataToMAVAndReturnProjects(ModelAndView modelAndView, HttpSession httpSession) {

        Long userId = (Long) httpSession.getAttribute("UserId");
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);

        String customerName = customerInfo.getName();

        Set<Project> projectsToShow = customerInfo.getProjects();

        List<Project> sortedProjectListToShow = new ArrayList<>(projectsToShow);
        sortedProjectListToShow.sort(new ProjectByDateComparator());

        //PUT OBJECTS TO MODEL
        modelAndView.addObject("customerName", customerName);
        modelAndView.addObject("projects", sortedProjectListToShow);

        return projectsToShow;
    }

    //PUT ALL CASES FROM PROJECTS TO ONE ARRAY;
    private void getCasesFromProject(Project project, ArrayList<Case> casesToShow) {

        Set<Case> cases = project.getCases();

        if (cases != null) {
            Iterator<Case> caseIterator = cases.iterator();
            while (caseIterator.hasNext()) {
                Case aCase = caseIterator.next();
                casesToShow.add(aCase);
            }
        }
    }


   /* @RequestMapping(value = "/project/{id}", method = RequestMethod.GET)
    public ModelAndView getProjectCases(@PathVariable Long id, HttpSession httpSession) {

        // GET CASES FROM PROJECT BY ID
        ModelAndView customerCabinetShowOneProject = new ModelAndView("customerAdminView/customerCabinet");

        addGeneralDataToMAVAndReturnProjects(customerCabinetShowOneProject , httpSession);

        ArrayList<Case> casesToShow = new ArrayList<>();

        Project project = projectService.getProjectById(id);
        getCasesFromProject(project, casesToShow);

        //SORT PROJECT & CASE
        Collections.sort(casesToShow, new CaseByStatusAndDateComparator());


        //GET PROJECT NAME
        String projectName = "";
        if (project.getProjectName().equals("#$GENERAL")){
            projectName = "Discussion Room";
        } else {
            projectName = project.getProjectName();
        }

        //PUT OBJECTS TO MODEL

        customerCabinetShowOneProject.addObject("currentProjectCasesName" , projectName);
        customerCabinetShowOneProject.addObject("projectId" , Long.toString(project.getId()));
        customerCabinetShowOneProject.addObject("cases", casesToShow);

        return customerCabinetShowOneProject;
    }*/


}