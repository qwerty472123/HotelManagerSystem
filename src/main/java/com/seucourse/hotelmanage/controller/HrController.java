package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Hr;
import com.seucourse.hotelmanage.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path = "/hr")
public class HrController extends CommonController {
    @Autowired
    private HrService hrService;

    @PostMapping(path = "/login")
    public String login(Hr hr, Model model, HttpSession session) throws NoSuchAlgorithmException {
        System.out.println("" + hr);
        Integer login = hrService.login(hr);
        if(login == 1) {
            session.setAttribute("hr", hr);
            return "success";
        }
        String msg = login==0?"pwd error" : "no user error";
        model.addAttribute("msg", msg);
        return "login";
    }
}
