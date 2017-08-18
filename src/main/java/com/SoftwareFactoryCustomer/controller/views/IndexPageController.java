package com.SoftwareFactoryCustomer.controller.views;

import com.SoftwareFactoryCustomer.comparator.EstimateByDateComparator;
import com.SoftwareFactoryCustomer.constant.*;
import com.SoftwareFactoryCustomer.model.*;
import com.SoftwareFactoryCustomer.service.*;
import com.SoftwareFactoryCustomer.util.AppMethods;
import com.SoftwareFactoryCustomer.util.SaveFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Date;


@Controller
@SessionAttributes("roles")
public class IndexPageController {


    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private ManagerInfoService managerInfoService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "isEstimateSuccess", required = false) Boolean isEstimateSuccess,
                                  @RequestParam(value = "isGenerateCustomerIdSuccess", required = false) Boolean isGenerateSuccess,
                                  @RequestParam(value = "isSessionExpired", required = false) Boolean isSessionExpired,
                                  @RequestParam(value = "isDelete", required = false) Boolean isDelete,
                                  HttpSession httpSession) {

        if (isCurrentAuthenticationAnonymous() || httpSession.getAttribute("UserName") != null || isDelete) {
            ModelAndView mainPage = new ModelAndView("index");

            if (isEstimateSuccess != null) {
                mainPage.addObject("isEstimateSuccess", isEstimateSuccess);
            }
            if (isGenerateSuccess != null) {
                mainPage.addObject("isGenerateCustomerIdSuccess", isGenerateSuccess);
            }
            if (isSessionExpired != null) {
                mainPage.addObject("isSessionExpired", isSessionExpired);
            }
            if (isDelete != null) {
                mainPage.addObject("isDelete", isDelete);
            }


            ArrayList<Estimate> estimateUnsorted = (ArrayList<Estimate>) estimateService.getAllEstimates();

            Collections.sort(estimateUnsorted, new EstimateByDateComparator());

            ArrayList<Estimate> estimatesSorted = getSixEstimatesFromArray(estimateUnsorted);
            mainPage.addObject("estimates", estimatesSorted);

            return mainPage;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/list");
            return modelAndView;
        }
    }


    @Autowired
    private MailService mailService;

    @ResponseBody
    @RequestMapping(value = "/estimate", method = RequestMethod.POST)
    public ModelAndView estimateWindow(@RequestParam("name") String recipientName,
                                       @RequestParam("email") String recipientMail,
                                       @RequestParam("phone") String phone,
                                       @RequestParam("message") String recipientRequestText,
                                       @RequestParam(value = "price_request", required = false) boolean priceRequest,
                                       @RequestParam(value = "question_request", required = false) boolean questionRequest,
                                       @RequestParam("fileEstimate[]") MultipartFile[] files,
                                       HttpServletRequest httpRequest) {

        if (recipientName == null && "".equals(recipientName) && recipientMail == null && "".equals(recipientMail) && phone == null && "".equals(phone)
                && recipientRequestText == null && "".equals(recipientRequestText)) {

            ModelAndView mainPageEstimateSuccess = new ModelAndView("redirect:/main");
            mainPageEstimateSuccess.addObject("isEstimateSuccess", new Boolean(false));
        }


        // CREATE ESTIMATE
        Estimate estimate = new Estimate();
        estimate.setName(recipientName);
        estimate.setEmail(recipientMail);
        estimate.setPhone(phone);
        estimate.setPriceRequest(priceRequest);
        estimate.setEstimateRequest(AppMethods.nl2br(recipientRequestText));
        estimate.setQuestionRequest(questionRequest);
        estimate.setDateRequest(new Date());
        estimate.setEstimateLinks(new HashSet<>());
        estimate.setRespond(false);

        estimateService.addNewEstimate(estimate);


        //GENERATE SPECIAL ESTIMATE ID
        String generatedEstimateId = generateEstimateId(estimate.getDateRequest(), estimate.getId());
        estimate.setEstimateGeneratedId(generatedEstimateId);

        estimateService.updateEstimate(estimate);

        //GENERATE CUSTOMER INFO ACCOUNT
        CustomerInfo customerInfo = generateCustomerInfo(phone, recipientMail, recipientName);

        //GENERATE REQUEST ID LINK FOR REGISTRATION
        String registrationLink = "www.sofac.kr/requestId/";
        registrationLink = registrationLink + customerInfo.getUser().getId() + "/" + generatedEstimateId + "/" + estimate.getId();


        //send email by naver style
        mailService.sendNaverMailAfterEstimate(generatedEstimateId, registrationLink, recipientMail);


        //Save to file

        SaveFile saveFile = new SaveFile(files);
        saveFile.saveEstimateFilesToEstimate(estimate);
        estimateService.updateEstimate(estimate);


        //ADD CUSTOMER INFO TO ESTIMATE
        estimate.setCustomerInfo(customerInfo);
        estimateService.updateEstimate(estimate);

        pushNotificationService.pushNotificationToGCM(recipientRequestText, MessageEnum.ESTIMATE.toString() + " :: " + estimate.getName(), new Date());

        //REDIRECT TO MAIN AND SHOW SUCCESS
        ModelAndView mainPageEstimateSuccess = new ModelAndView("redirect:/main");
        mainPageEstimateSuccess.addObject("isEstimateSuccess", new Boolean(true));

        return mainPageEstimateSuccess;
    }

    @RequestMapping(value = "/requestId/{userId}/{generatedEstimateId}/{estimateId}", method = RequestMethod.GET)
    public ModelAndView requestIdShowPage(@PathVariable Long userId,
                                          @PathVariable Long generatedEstimateId,
                                          @PathVariable Long estimateId) {

        User user = userService.findById(userId);
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);

        if (user == null || customerInfo.isFullCreated()) {
            return new ModelAndView("redirect:/main");
        }

        ModelAndView requestIdPage = new ModelAndView("/requestId");

        Estimate estimate = estimateService.getEstimateById(estimateId);
        requestIdPage.addObject("CustomerEstimate", estimate);
        requestIdPage.addObject("User", user);

        return requestIdPage;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @RequestMapping(value = "/generateCustomerId", method = RequestMethod.POST)
    public ModelAndView requestIdCreateAccount(@RequestParam("userId") Long userId, @RequestParam("name") String name, @RequestParam("email") String email,
                                               @RequestParam("phone") String phone, @RequestParam("companyName") String companyName, @RequestParam("companySite") String companySite) {

        ModelAndView main = new ModelAndView("redirect:/main");

        String password = phone.replace(" ", "");
        password = password.replace(")", "");
        password = password.replace("(", "");
        password = password.replace("-", "");

        //UPDATE USER PASSWORD RELATED TO CUSTOMER
        User user = userService.findById(userId);
        user.setPassword(password);
        userService.updateUser(user);

        //UPDATE CUSTOMER INFO AFTER FULL REGISTRATION
        Long customerInfoId = user.getId();
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(customerInfoId);
        customerInfo.setName(name);
        customerInfo.setEmail(email);
        customerInfo.setPhone(phone);
        customerInfo.setCompany(companyName);
        customerInfo.setWebsite(companySite);
        customerInfo.setFullCreated(true);
        customerInfoService.updateCustomerInfo(customerInfo);


        //SEND INFORMATION TO CUSTOMER by naver style
        mailService.sendNaverMailAfterRegistration(password, user.getSsoId(), email, name);

        main.addObject("isGenerateCustomerIdSuccess", new Boolean(true));
        return main;
    }

    @RequestMapping(value = "/session_expired", method = RequestMethod.POST)
    public ModelAndView sessionExpired() {
        ModelAndView modelAndView = new ModelAndView("redirect:/main");

        modelAndView.addObject("isSessionExpired", new Boolean(true));

        return modelAndView;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }


    private ArrayList<Estimate> getSixEstimatesFromArray(ArrayList<Estimate> sortedEstimateList) {

        ArrayList<Estimate> estimateToShow = new ArrayList<>();
        if (sortedEstimateList.size() >= 6) {
            for (int i = 0; i < 6; i++) {
                estimateToShow.add(sortedEstimateList.get(i));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                estimateToShow.add(null);
            }
            for (int i = 0; i < sortedEstimateList.size(); i++) {
                estimateToShow.set(i, sortedEstimateList.get(i));
            }
        }
        return estimateToShow;
    }

    private String generateEstimateId(Date currentDate, long id) {

        java.sql.Date date = new java.sql.Date(currentDate.getTime());

        String dateWithoutHours = date.toString();
        dateWithoutHours = dateWithoutHours.substring(2, 10);
        dateWithoutHours = dateWithoutHours.replaceAll("-", "");

        String stringId = Long.toString(id);

        String generatedEstimateId = "";
        if (stringId.length() <= 4) {
            String zero = "";
            for (int i = 0; i < 4 - stringId.length(); i++) {
                zero = zero + "0";
                System.out.println("1");
            }
            generatedEstimateId = dateWithoutHours + zero + stringId;
        } else {
            generatedEstimateId = dateWithoutHours + stringId;
        }
        System.out.println(generatedEstimateId);
        return generatedEstimateId;
    }


    private CustomerInfo generateCustomerInfo(String phone, String recipientMail, String recipientName) {

        // CREATE CUSTOMER USER
        User userAfterSave = userService.createCustomerUser(phone);


        //CREATE FINAL NEW CUSTOMER
        CustomerInfo customerInfo = new CustomerInfo(userAfterSave.getId(), userAfterSave, recipientName, "", phone, recipientMail, "", false, true, "", "", "", "", "", new Date());
        customerInfoService.addNewCustomerInfo(customerInfo);
        CustomerInfo customerInfoCreated = customerInfoService.getCustomerInfoById(userAfterSave.getId());

        ManagerInfo managerInfo = managerInfoService.getManagerInfoById(3L);

        //CREATE #$GENERAL PROJECT FOR CUSTOMER
        Project projectNormal = new Project(ProjectEnum.projectNameNormal.getDbValue(), new Date(), StatusEnum.OPEN.toString(), customerInfo,
                new HashSet<>(), "test", new Date(), null, "Default Normal project", managerInfo, "", "", "");
        Project projectEstimate = new Project(ProjectEnum.projectNameEstimate.getDbValue(), new Date(), StatusEnum.OPEN.toString(), customerInfo,
                new HashSet<>(), "test", new Date(), null, "Default Estimate project", managerInfo, "", "", "");
        Set<Project> projects = new HashSet<>();
        projects.add(projectNormal);
        projects.add(projectEstimate);
        customerInfoCreated.setProjects(projects);


        customerInfoService.updateCustomerInfo(customerInfoCreated);
        return customerInfo;
    }


    private static final boolean isMultipartContent(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null) {
            return false;
        }
        if (contentType.toLowerCase().startsWith("multipart/")) {
            return true;
        }
        return false;
    }
}
