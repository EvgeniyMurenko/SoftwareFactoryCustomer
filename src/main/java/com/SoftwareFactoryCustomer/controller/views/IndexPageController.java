package com.SoftwareFactoryCustomer.controller.views;

import com.SoftwareFactoryCustomer.comparator.EstimateByDateComparator;
import com.SoftwareFactoryCustomer.constant.GlobalEnum;
import com.SoftwareFactoryCustomer.constant.MainPathEnum;
import com.SoftwareFactoryCustomer.constant.ProjectEnum;
import com.SoftwareFactoryCustomer.constant.StatusEnum;
import com.SoftwareFactoryCustomer.model.*;
import com.SoftwareFactoryCustomer.util.SaveFile;
import com.SoftwareFactoryCustomer.service.CustomerInfoService;
import com.SoftwareFactoryCustomer.service.EstimateService;
import com.SoftwareFactoryCustomer.service.MailService;
import com.SoftwareFactoryCustomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.Date;


@Controller
@SessionAttributes("roles")
public class IndexPageController {


    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    EstimateService estimateService;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "isEstimateSuccess", required = false) Boolean isEstimateSuccess ,
                                  @RequestParam(value = "isGenerateCustomerIdSuccess" , required=false) Boolean isGenerateSuccess ,
                                  @RequestParam(value ="isSessionExpired" , required = false) Boolean isSessionExpired ) {

        if (isCurrentAuthenticationAnonymous()) {
            ModelAndView mainPage = new ModelAndView("index");

            if (isEstimateSuccess != null){
                mainPage.addObject("isEstimateSuccess" , isEstimateSuccess);
            }
            if (isGenerateSuccess !=null){
                mainPage.addObject("isGenerateCustomerIdSuccess" , isGenerateSuccess);
            }
            if (isSessionExpired !=null){
                mainPage.addObject("isSessionExpired" , isSessionExpired);
            }

            ArrayList<Estimate> estimateUnsorted = (ArrayList<Estimate>) estimateService.getAllEstimates();

            Collections.sort(estimateUnsorted, new EstimateByDateComparator());

            ArrayList<Estimate> estimatesSorted = getSixEstimatesFromArray(estimateUnsorted);
            mainPage.addObject("estimates" ,estimatesSorted );
            return mainPage;
        } else {
            ModelAndView modelAndView = new ModelAndView("redirect:/list");
            return modelAndView;
        }
    }


    @Autowired
    MailService mailService;

    @RequestMapping(value = "/estimate", method = RequestMethod.POST)
    public @ResponseBody ModelAndView estimateWindow(@RequestParam("name") String recipientName, @RequestParam("email") String recipientMail, @RequestParam("phone") String phone,
                                                     @RequestParam("message") String recipientRequestText, @RequestParam(value = "price_request", required = false) boolean priceRequest,
                                                     @RequestParam(value = "question_request", required = false) boolean questionRequest, Model model,
                                                     @RequestParam("fileEstimate[]") MultipartFile[] files) {


        System.out.println("name " + recipientName + " email " + recipientMail + " text "
                + recipientRequestText + "phone" + phone);

        System.out.println("========================START ESTIMATE CREATE");
        System.out.println("========================ESTIMATE NAME: "+ recipientName);


        // CREATE ESTIMATE
        Estimate estimate = new Estimate();
        estimate.setName(recipientName);
        estimate.setEmail(recipientMail);
        estimate.setPhone(phone);
        estimate.setPriceRequest(priceRequest);
        estimate.setEstimateRequest(recipientRequestText);
        estimate.setQuestionRequest(questionRequest);
        estimate.setDateRequest(new Date());
        estimate.setEstimateLinks(new HashSet<>());
        estimate.setRespond(false);

        estimateService.addNewEstimate(estimate);


        //GENERATE SPECIAL ESTIMATE ID
        String generatedEstimateId = generateEstimateId(estimate.getDateRequest() , estimate.getId());
        estimate.setEstimateGeneratedId(generatedEstimateId);

        estimateService.updateEstimate(estimate);

        //GENERATE CUSTOMER INFO ACCOUNT
        CustomerInfo customerInfo =  generateCustomerInfo( phone , recipientMail , recipientName);

        //GENERATE REQUEST ID LINK FOR REGISTRATION
        String registrationLink = "www.sofac.kr/requestId/";
        registrationLink = registrationLink + customerInfo.getUser().getId() + "/" +generatedEstimateId +"/" + estimate.getId()  ;

        //SEND EMAIL TO CUSTOMER WITH DETAILS
        mailService.sendEmailAfterEstimate(generatedEstimateId, registrationLink , recipientMail);

        //Save to file

        SaveFile saveFile = new SaveFile(files);
        saveFile.saveEstimateFilesToEstimate(estimate);
        estimateService.updateEstimate(estimate);


        //ADD CUSTOMER INFO TO ESTIMATE
        estimate.setCustomerInfo(customerInfo);
        estimateService.updateEstimate(estimate);

        //REDIRECT TO MAIN AND SHOW SUCCESS
        ModelAndView mainPageEstimateSuccess = new ModelAndView("redirect:/main");
        mainPageEstimateSuccess.addObject("isEstimateSuccess", new Boolean(true));

        return mainPageEstimateSuccess;
    }

    @RequestMapping(value = "/requestId/{userId}/{generatedEstimateId}/{estimateId}", method = RequestMethod.GET)
    public ModelAndView requestIdShowPage(@PathVariable Long userId ,
                                          @PathVariable Long generatedEstimateId ,
                                          @PathVariable Long estimateId){

        User user = userService.findById(userId);

        if (user == null || user.isFullCreated()){
            return new ModelAndView("redirect:/main");
        }

        ModelAndView requestIdPage = new ModelAndView("/requestId");

        Estimate estimate = estimateService.getEstimateById(estimateId);
        requestIdPage.addObject("CustomerEstimate" , estimate);
        requestIdPage.addObject("User" , user);

        return requestIdPage;
    }

    @Autowired
    UserService userService;

    @Autowired
    CustomerInfoService customerInfoService;

    @RequestMapping(value = "/generateCustomerId", method = RequestMethod.POST)
    public ModelAndView requestIdCreateAccount(@RequestParam("userId") Long userId , @RequestParam("name") String name, @RequestParam("email") String email,
                                               @RequestParam("phone") String phone , @RequestParam("companyName") String companyName, @RequestParam("companySite") String companySite){

        ModelAndView main = new ModelAndView("redirect:/main");

        String password = phone.replace(" " , "");
        password = password.replace(")" ,"");
        password = password.replace("(" , "");
        password = password.replace("-" ,"");

        //UPDATE USER PASSWORD RELATED TO CUSTOMER
        User user = userService.findById(userId);
        user.setPassword(password);
        user.setFullCreated(true);
        userService.updateUser(user);

        //UPDATE CUSTOMER INFO AFTER FULL REGISTRATION
        Long customerInfoId = user.getId();
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(customerInfoId);
        customerInfo.setName(name);
        customerInfo.setEmail(email);
        customerInfo.setPhone(phone);
        customerInfo.setCompany(companyName);
        customerInfo.setWebsite(companySite);

        customerInfoService.updateCustomerInfo(customerInfo);


        //SEND INFORMATION TO CUSTOMER
        mailService.sendEmailAfterRegistration(password , user.getSsoId() , email , name);

        main.addObject("isGenerateCustomerIdSuccess" , new Boolean(true));
        return main;
    }

    @RequestMapping(value = "/session_expired", method = RequestMethod.POST)
    public ModelAndView sessionExpired( ){
        ModelAndView modelAndView = new ModelAndView("redirect:/main");

        modelAndView.addObject("isSessionExpired" , new Boolean(true));

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

    private String generateEstimateId(Date currentDate , long id) {

        java.sql.Date date = new java.sql.Date(currentDate.getTime());

        String dateWithoutHours = date.toString();
        dateWithoutHours = dateWithoutHours.substring(2 ,10);
        dateWithoutHours = dateWithoutHours.replaceAll("-","");

        String stringId = Long.toString(id);

        String generatedEstimateId = "";
        if (stringId.length() <= 4){
            String zero = "";
            for (int i = 0; i < 4-stringId.length(); i++){
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


    private CustomerInfo generateCustomerInfo(String phone , String recipientMail , String recipientName ){

        // CREATE CUSTOMER USER
        User userAfterSave = userService.createCustomerUser(phone);


        //CREATE FINAL NEW CUSTOMER
        CustomerInfo customerInfo = new CustomerInfo(userAfterSave, recipientName, "", phone, recipientMail, "" , new HashSet<>());
        customerInfo.setId(userAfterSave.getId());
        customerInfoService.addNewCustomerInfo(customerInfo);
        CustomerInfo customerInfoCreated = customerInfoService.getCustomerInfoById(userAfterSave.getId());

        //CREATE #$GENERAL PROJECT FOR CUSTOMER
        Project projectNormal = new Project(ProjectEnum.projectNameNormal.getDbValue(), new Date(), StatusEnum.OPEN.toString(), customerInfo, new HashSet<>(), "test");
        Project projectEstimate = new Project(ProjectEnum.projectNameEstimate.getDbValue(), new Date(), StatusEnum.OPEN.toString(), customerInfo, new HashSet<>(), "test");

        Set<Project> projects = new HashSet<>();
        projects.add(projectNormal);
        projects.add(projectEstimate);
        customerInfoCreated.setProjects(projects);


        customerInfoService.updateCustomerInfo(customerInfoCreated);
        return customerInfo;
    }
}