package com.seucourse.hotelmanage.interceptor;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId != null) {
            User user = userService.getUser(userId);
            if (user != null) {
                System.out.println("path for " + request.getServletPath());
                if (request.getServletPath().equals("/user/logout")) return true;
                if (request.getServletPath().startsWith("/" + EnumUtil.getRoleDesc(user.getRole())))
                    return true;
            }
        }
        response.sendRedirect("/");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
