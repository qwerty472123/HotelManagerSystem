package com.myexample.bootwithmybatis.service.impl;

import com.myexample.bootwithmybatis.entity.Dept;
import com.myexample.bootwithmybatis.entity.Emp;
import com.myexample.bootwithmybatis.mapper.DeptMapper;
import com.myexample.bootwithmybatis.mapper.EmpMapper;
import com.myexample.bootwithmybatis.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public void addDept(Dept dept) {
        System.out.println("deptServiceImpl is called");
        dept.setStatus(1);
        deptMapper.insertDept(dept);
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
    }

    @Override
    public Dept queryDeptByDeptId(Integer deptId) {
        return deptMapper.selectDeptByDeptId(deptId);
    }

    @Override
    public List<Dept> queryAllDept(Dept dept) {
        return deptMapper.selectAllDepts(dept);
    }

    @Override
    public String deleteDept(Integer deptId) {
        Dept dept = deptMapper.selectDeptByDeptId(deptId);
        List<Emp> emps = dept.getEmpList();
        if (emps.size() == 0) {
            dept.setStatus(0);
            deptMapper.updateDept(dept);
            return "success";
        }
        return "has worker";
    }
}
