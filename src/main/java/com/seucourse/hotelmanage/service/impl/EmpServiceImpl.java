package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.mapper.EmpMapper;
import com.seucourse.hotelmanage.mapper.UserMapper;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer addEmp(Emp emp){
        User user = emp.getUser();
        Integer status = userService.register(user);
        if (status != 0) return status;
        emp.setUserId(user.getId());
        this.empMapper.insertEmp(emp);
        return 0;
    }

    @Override
    public List<Emp> listEmps(Emp emp) {
        return empMapper.selectEmp(emp);
    }

    @Override
    public Integer deleteEmp(Integer id) {
        List<Emp> emps = empMapper.selectEmp(Emp.builder().id(id).build());
        if(emps.size()!=1)return -1;
        Emp emp = emps.get(0);
        userMapper.deleteUser(User.builder().id(emp.getUserId()).build());
        empMapper.deleteEmp(id);
        return 0;
    }

    @Override
    public String updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
        return userService.updateUser(emp.getUser());
    }
}
