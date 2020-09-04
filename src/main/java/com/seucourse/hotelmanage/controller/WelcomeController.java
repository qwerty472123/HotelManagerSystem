package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class WelcomeController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public String welcome(HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        if(userId != null) {
            User user = userService.getUser(userId);
            if (user != null) {
                return "redirect:/" + EnumUtil.getRoleDesc(user.getRole()) + "/";
            } else {
                session.removeAttribute("userId");
            }
        }
        return "login";
    }
}
