package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("登录用户："+user);
        Integer status = userService.login(user);
        System.out.println("登录验证结果："+status);
        if (status == 0) {
            session.setAttribute("userId", user.getId());
            return "redirect:/" + EnumUtil.getRoleDesc(user.getRole()) + "/";
        }
        model.addAttribute("errorId", status);
        return "login";
    }

    @GetMapping(path = "/logout")
    public String doLogout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/";
    }
}
