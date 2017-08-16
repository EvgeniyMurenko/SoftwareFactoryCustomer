package com.SoftwareFactoryCustomer.controller;

import com.SoftwareFactoryCustomer.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;


@ControllerAdvice
@RequestMapping("/error")
public class AdviceExceptionController {


    @Autowired
    MailService mailService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAll(Exception ex) {


        if (!FileNotFoundException.class.isInstance(ex)) {

            StringWriter sw = new StringWriter();
            ex.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            mailService.sendBugExceptionToEmail(exceptionAsString);

        }


        ModelAndView exceptionView;
        if (NoHandlerFoundException.class.isInstance(ex)) {
            exceptionView = new ModelAndView("redirect:/error/404");
        } else {
            exceptionView = new ModelAndView("404");
            exceptionView.addObject("number", ex);
            exceptionView.addObject("error", ex.getMessage());
        }
        return exceptionView;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/error/404";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public ModelAndView NotFoundPage() {
        return new ModelAndView("404");
    }
}
