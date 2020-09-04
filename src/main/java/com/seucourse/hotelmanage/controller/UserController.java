package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path = "/login")
    public String doLogin(User user, Model model, HttpSession session) {
        Integer status = userService.login(user);
        if (status == 0) {
            session.setAttribute("userId", user.getId());
            return "redirect:/" + user.getRole() + "/";
        }
        model.addAttribute("errorId", status);
        return "login";
    }
}
