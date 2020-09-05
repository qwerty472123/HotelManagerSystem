package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

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
    public String preAddWorker(Model model) {
        model.addAttribute("tab",2);
        return "manager_welcome";
    }

    @PostMapping(path="/addWorker")
    @ResponseBody
    public Integer addWorker(Emp emp, User user) {
        emp.setUser(user);
        return empService.addEmp(emp);
    }

    @PostMapping(path="/delete")
    @ResponseBody
    public Integer deleteWorker(Integer id) {
        return empService.deleteEmp(id);
    }

    @GetMapping(path="/showList")
    public String showList(Model model) {
        model.addAttribute("tab",1);
        model.addAttribute("emps", empService.listEmps(Emp.builder().build()));
        return "manager_welcome";
    }
}
