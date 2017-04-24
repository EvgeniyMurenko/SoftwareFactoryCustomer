package com.SoftwareFactoryCustomer.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
@RequestMapping("/error")
public class AdviceExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/error/404";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public ModelAndView NotFoundPage() {
        return new ModelAndView("404");
    }
}
