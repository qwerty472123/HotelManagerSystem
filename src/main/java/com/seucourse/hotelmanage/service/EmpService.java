package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Emp;

import java.util.List;

public interface EmpService {
    Integer addEmp(Emp emp);

    List<Emp> listEmps(Emp emp);

    Integer deleteEmp(Integer id);

    String updateEmp(Emp emp);
}
