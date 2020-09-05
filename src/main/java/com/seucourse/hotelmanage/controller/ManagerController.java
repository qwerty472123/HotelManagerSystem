package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/manager")
public class ManagerController {
    @Autowired
    private EmpService empService;

    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public String welcome(Model model) {
        model.addAttribute("tab", 0);
        return "manager_welcome";
    }

    @GetMapping(path = "/addWorker")
    public String preAddWorker(Model model) {
        model.addAttribute("tab", 2);
        return "manager_welcome";
    }

    @PostMapping(path = "/addWorker")
    @ResponseBody
    public Integer addWorker(Emp emp, User user) {
        emp.setUser(user);
        return empService.addEmp(emp);
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    public Integer deleteWorker(Integer id, Model model) {
        return empService.deleteEmp(id);
    }

    @GetMapping(path = "/update/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        List<Emp> emps = empService.listEmps(Emp.builder().id(id).build());
        if (emps.size() != 1) return "redirect:/manager/showList";
        Emp emp = emps.get(0);
        model.addAttribute("tab", 3);
        model.addAttribute("emp", emp);
        return "manager_welcome";
    }

    @PostMapping(path = "/update/{id}")
    @ResponseBody
    public String doUpdate(@PathVariable("id") Integer id, Emp emp, User user) {
        List<Emp> emps = empService.listEmps(Emp.builder().id(id).build());
        if (emps.size() != 1) return "无此用户";
        Emp empDB = emps.get(0);
        emp.setUserId(empDB.getUserId());
        emp.setId(id);
        emp.setUser(user);
        user.setId(empDB.getUserId());
        return empService.updateEmp(emp);
    }

    @GetMapping(path = "/showList")
    public String showList(Model model) {
        model.addAttribute("tab", 1);
        model.addAttribute("emps", empService.listEmps(Emp.builder().build()));
        return "manager_welcome";
    }
}
