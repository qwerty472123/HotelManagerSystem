package com.myexample.bootwithmybatis.service;

import com.myexample.bootwithmybatis.entity.Dept;

import java.util.List;

public interface DeptService {
    void addDept(Dept dept);

    void updateDept(Dept dept);

    Dept queryDeptByDeptId(Integer deptId);

    List<Dept> queryAllDept(Dept dept);

    String deleteDept(Integer deptId);
}
