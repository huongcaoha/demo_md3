package com.ra.base_spring_mvc.config;

import com.ra.base_spring_mvc.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorUser implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        User user = (User) request.getSession().getAttribute("user");
//        if(user != null){
//            return  true;
//        }else {
//            response.sendRedirect("/login");
//            return false;
//        }
        return true ;

    }
}
