package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/manager")
public class ManagerController {
    @Autowired
    private EmpService empService;

    @Autowired
    private UserService userService;

    @GetMapping(path="/")
    public String welcome(Model model){
        model.addAttribute("tab",0);
        return "manager_welcome";
    }

    @GetMapping(path="/addWorker")
    public String addWorker(Model model) {
        model.addAttribute("tab",2);
        return "manager_welcome";
    }

    @GetMapping(path="/showList")
    public String showList(Model model) {
        model.addAttribute("tab",1);
        model.addAttribute("emps", empService.listEmps(Emp.builder().build()));
        return "manager_welcome";
    }
}
