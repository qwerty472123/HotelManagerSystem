package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/guest")
public class GuestController {
    @Autowired
    private UserService userService;
    @GetMapping(path = "register")
    public String preRegister() {
        return "guest_register";
    }
    @PostMapping(path = "register")
    public String doRegister(User user, Model model) {
        user.setRole(0);
        Integer status = userService.register(user);
        if (status == 0) {
            return "/user/login";
        }
        model.addAttribute("errorId", status);
        return "guest_register";
    }
}
