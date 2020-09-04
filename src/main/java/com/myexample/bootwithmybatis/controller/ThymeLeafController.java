package com.myexample.bootwithmybatis.controller;

import com.myexample.bootwithmybatis.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/thy")
public class ThymeLeafController {
    @Resource
    private DeptService deptService;

    @GetMapping(path = "/first")
    public String firstPage(Model model){
        model.addAttribute("attr1", "info");
        List<Integer>x=new ArrayList<Integer>();
        x.add(1);
        x.add(8);
        model.addAttribute("attr2", x);
        model.addAttribute("cur", new Date());

        model.addAttribute("depts", deptService.queryAllDept(null));
        return "firstThymeleaf";
    }

    @GetMapping(path = "/getInfo")
    public String getInfo(Model model, Integer uid){
        System.out.println("uid = " + uid);
        return firstPage(model);
    }

    @GetMapping(path = "/getREST/{uid}")
    public String getRESTInfo(@PathVariable("uid") Integer uid){
        System.out.println("uid = " + uid);
        return "firstThymeleaf";
    }
}
