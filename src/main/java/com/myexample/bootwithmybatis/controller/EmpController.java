package com.myexample.bootwithmybatis.controller;

import com.myexample.bootwithmybatis.entity.Dept;
import com.myexample.bootwithmybatis.entity.Emp;
import com.myexample.bootwithmybatis.service.DeptService;
import com.myexample.bootwithmybatis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
