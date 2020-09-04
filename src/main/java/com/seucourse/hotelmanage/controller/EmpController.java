package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Dept;
import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.service.DeptService;
import com.seucourse.hotelmanage.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.NoSuchAlgorithmException;

@Controller
@RequestMapping(path = "/emp")
public class EmpController extends CommonController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private EmpService empService;

    @GetMapping(path = "/preAdd")
    public String addEmp(Model model){
        model.addAttribute("depts", deptService.queryAllDept(Dept.builder().status(1).build()));
        return "addEmp";
    }
    @PostMapping(path = "/add")
    public String addEmp(Emp emp){
        System.out.println("new emp " + emp);
        try {
            empService.addEmp(emp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //return "forward:/dept/showList";
        return "redirect:/dept/showInfo/" + emp.getDeptId();
    }
}
