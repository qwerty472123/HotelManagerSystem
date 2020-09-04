package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Dept;
import com.seucourse.hotelmanage.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptServiceImpl2")
public class DeptServiceImpl2 implements DeptService {
    @Override
    public void addDept(Dept dept) {
        System.out.println("deptServiceImpl2 is called");
    }

    @Override
    public void updateDept(Dept dept) {

    }

    @Override
    public Dept queryDeptByDeptId(Integer deptId) {
        return null;
    }

    @Override
    public List<Dept> queryAllDept(Dept dept) {
        return null;
    }

    @Override
    public String deleteDept(Integer deptId) {
        return null;
    }
}
