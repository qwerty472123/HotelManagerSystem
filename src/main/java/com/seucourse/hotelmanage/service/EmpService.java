package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Emp;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface EmpService {
    void addEmp(Emp emp);
    List<Emp> listEmps(Emp emp);
}
