package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.mapper.EmpMapper;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private UserService userService;

    @Override
    public void addEmp(Emp emp){
        emp.setHireDate(new Date());
        User user = emp.getUser();
        Integer status = userService.register(user);

        this.empMapper.insertEmp(emp);


    }
}
