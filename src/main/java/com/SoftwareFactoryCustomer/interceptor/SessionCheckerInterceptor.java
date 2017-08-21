package com.SoftwareFactoryCustomer.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class SessionCheckerInterceptor implements HandlerInterceptor {

    private static final String LOGIN_REQUEST = "/list/";

    private static final String MAIN = "/main";

    private static final String ROOT = "/";


    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();

        if (!MAIN.equals(uri) && !LOGIN_REQUEST.equals(uri) && !ROOT.equals(uri)) {


            if (!UserInterceptor.isUserLogged()) {

                session = request.getSession();

                if (session.getAttribute("managerInfo") == null) {
                    System.out.println("Logging out, due to inactive session");
                    SecurityContextHolder.clearContext();
                    System.out.println("clearContext");
                    request.logout();

                    response.sendRedirect("/main");
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView model) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}

