package com.myexample.bootwithmybatis.controller;

import com.myexample.bootwithmybatis.entity.Dept;
import com.myexample.bootwithmybatis.service.DeptService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(path = "/dept")
public class DeptController extends CommonController {
    @Resource
    private DeptService deptService;

    @GetMapping(path = "/preAdd")
    public String toAdd(){
        return "addDept";
    }

    @PostMapping(path = "/add")
    public String addDept(Dept dept){
        deptService.addDept(dept);
        //return "forward:/dept/showList";
        return "redirect:/dept/showList";
    }

    @RequestMapping(path = "/showList", method = {RequestMethod.GET, RequestMethod.POST})
    public String showList(Model model, Dept dept){
        if(dept==null)dept=new Dept();
        model.addAttribute("depts", deptService.queryAllDept(dept));
        model.addAttribute("query", dept);
        return "deptList";
    }

    @GetMapping(path = "/showInfo/{deptId}")
    public String showInfo(@PathVariable("deptId") Integer deptId, Model model) {
        System.out.println("querying " + deptId);
        Dept dept = deptService.queryDeptByDeptId(deptId);
        model.addAttribute("dept", dept);
        return "deptInfo";
    }

    @GetMapping(path = "/getInfo/{deptId}")
    @ResponseBody
    public Dept getInfo(@PathVariable("deptId") Integer deptId) {
        System.out.println("querying " + deptId);
        Dept dept = deptService.queryDeptByDeptId(deptId);
        return dept;
    }

    @GetMapping(path = "/preUpdate/{deptId}")
    public String preUpdate(@PathVariable("deptId") Integer deptId, Model model) {
        System.out.println("the dept need to change is " + deptId);
        model.addAttribute("dept",deptService.queryDeptByDeptId(deptId));
        return "updateDept";
    }

    @PostMapping(path = "/update")
    public String update(Dept dept, Model model) {
        System.out.println("the dept updating is #{dept.getDeptId()}" + dept.getDeptId());
        deptService.updateDept(dept);
        return "redirect:/dept/showInfo/" + dept.getDeptId();
    }

    @GetMapping(path = "/disabled/{deptId}")
    @ResponseBody
    public String disableDept(@PathVariable("deptId") Integer deptId) {
        System.out.println("dept to disable: "+ deptId);
        return deptService.deleteDept(deptId);
    }
}
