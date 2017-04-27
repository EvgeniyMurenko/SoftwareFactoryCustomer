package com.SoftwareFactoryCustomer.controller;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SoftwareFactoryCustomer.constant.MainPathEnum;
import com.SoftwareFactoryCustomer.model.Message;
import com.SoftwareFactoryCustomer.model.User;
import com.SoftwareFactoryCustomer.model.UserProfile;
import com.SoftwareFactoryCustomer.service.MessageService;
import com.SoftwareFactoryCustomer.service.UserProfileService;
import com.SoftwareFactoryCustomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;


    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public ModelAndView listUsers(HttpSession session) {

        User currentUser = userService.findBySSO(getPrincipal());

        Set profiles = currentUser.getUserProfiles();

        UserProfile userProfile = null;
        Iterator iterator = profiles.iterator();
        while (iterator.hasNext()) {
            userProfile = (UserProfile) iterator.next();
        }

        ModelAndView modelAndView = new ModelAndView();

        if (userProfile.getType().equals("CUSTOMER")) {
            System.out.println("LOGIN AS CUSTOMER");
            modelAndView.setViewName("redirect:/cabinet/");
        } else {
            modelAndView.setViewName("redirect:/");
        }

        System.out.println(currentUser.getId());
        System.out.println(userProfile.getType());

        session.setAttribute("UserId", currentUser.getId());
        session.setAttribute("UserRole", userProfile.getType());

        return modelAndView;
    }


    /**
     * This method will provide UserProfile list to views
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    /**
     * This method handles logout requests.
     * Toggle the handlers if you are RememberMe functionality is useless in your app.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        request.getSession().invalidate();
        return "redirect:/main?logout";
    }

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/get-file/{type}/{filename}", method = RequestMethod.GET)
    public void getFile(HttpServletResponse response,
                        @PathVariable String type,
                        @PathVariable String filename) throws IOException {

        String EXTERNAL_FILE_PATH = MainPathEnum.mainPath + "/" + type + "/" + filename;

        File file = new File(EXTERNAL_FILE_PATH);

        checkFile(file, response);

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }

        System.out.println("mimetype : " + mimeType);

        response.setContentType(mimeType);

        response.setHeader("Content-Disposition : attachment", String.format("inline; filename=\"" + file.getName() + "\""));


        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));


        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    private void checkFile(File file, HttpServletResponse response) {
        if (!file.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                return;
            }

        }
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
}


