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


    private static final long MAX_INACTIVE_SESSION_TIME = 863990;


    @Autowired
    private HttpSession session;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        System.out.println("Pre handle method - check handling start time");
        long startTime = System.currentTimeMillis();
        request.setAttribute("executionTime", startTime);



        if (UserInterceptor.isUserLogged()) {
            session = request.getSession();

           System.out.print("End Time - " + "current time  - "+System.currentTimeMillis() +"  last accessed -   " +  session.getLastAccessedTime() + " ==  " +(System.currentTimeMillis() - session.getLastAccessedTime() ));

            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {

                System.out.println("Logging out, due to inactive session");
                SecurityContextHolder.clearContext();
                System.out.println("clearContext");
                request.logout();
                System.out.println("logout");
                response.sendRedirect("/");
                return false;
            }
        }
        System.out.println("sendRedirect");
        return true;
    }






    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView model) throws Exception {
        System.out.println("Post handle method - check execution time of handling");
      /*  long startTime = (Long) request.getAttribute("executionTime");*/

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
