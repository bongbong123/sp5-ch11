package com.example.sp5ch11.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Object authInfo = session.getAttribute("authInfo");
            System.out.println("intercepter teset1");
            if (authInfo != null) {
                return true;
            }
        }

        System.out.println("intercepter test2");
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
