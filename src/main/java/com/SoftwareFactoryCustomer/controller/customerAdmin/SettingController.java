package com.SoftwareFactoryCustomer.controller.customerAdmin;

import com.SoftwareFactoryCustomer.model.CustomerInfo;
import com.SoftwareFactoryCustomer.model.User;
import com.SoftwareFactoryCustomer.service.CustomerInfoService;
import com.SoftwareFactoryCustomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cabinet")
@SessionAttributes("roles")
public class SettingController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerInfoService customerInfoService;

    @RequestMapping(value = "/settings/", method = RequestMethod.GET)
    public ModelAndView customerSettings(HttpSession httpSession) {

        Long userId = (Long) httpSession.getAttribute("UserId");

        ModelAndView customerSettings = new ModelAndView("customerAdminViews/customerSettings");

        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);
        User user = userService.findById(userId);

        customerSettings.addObject("user", user);
        customerSettings.addObject("customerInfo", customerInfo);
        customerSettings.addObject("customerName" , customerInfo.getName());

        return customerSettings;
    }

    @RequestMapping(value = "/infoSettings", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView infoSettings(HttpSession httpSession, @RequestParam("name") String recipientName, @RequestParam("email") String recipientMail,
                              @RequestParam("phone") String phone, @RequestParam("companyName") String companyName,
                              @RequestParam("companySite") String companySite) {

        Long userId = (Long) httpSession.getAttribute("UserId");
        CustomerInfo customerInfo = customerInfoService.getCustomerInfoById(userId);

        customerInfo.setName(recipientName);
        customerInfo.setEmail(recipientMail);
        customerInfo.setPhone(phone);
        customerInfo.setCompany(companyName);
        customerInfo.setWebsite(companySite);

        customerInfoService.updateCustomerInfo(customerInfo);

        return new ModelAndView("redirect:/cabinet/settings/");
    }

    @RequestMapping(value = "/passwordSettings", method = RequestMethod.POST)
    public @ResponseBody ModelAndView infoSettings(HttpSession httpSession,
                                                   @RequestParam("newPassword") String newPassword,
                                                   @RequestParam("confirmNewPassword") String confirmNewPassword) {

        Long userId = (Long) httpSession.getAttribute("UserId");
        User user = userService.findById(userId);
        if(newPassword.equals(confirmNewPassword) ){
            user.setPassword(newPassword);
            userService.updateUser(user);
        }

        return new ModelAndView("redirect:/cabinet/settings/");
    }
}
