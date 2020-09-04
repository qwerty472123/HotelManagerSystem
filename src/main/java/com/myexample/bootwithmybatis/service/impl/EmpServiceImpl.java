package com.myexample.bootwithmybatis.service.impl;

import com.myexample.bootwithmybatis.entity.Emp;
import com.myexample.bootwithmybatis.entity.Hr;
import com.myexample.bootwithmybatis.mapper.EmpMapper;
import com.myexample.bootwithmybatis.mapper.HrMapper;
import com.myexample.bootwithmybatis.service.EmpService;
import com.myexample.bootwithmybatis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service("empService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private HrMapper hrMapper;
    @Override
    public void addEmp(Emp emp) throws NoSuchAlgorithmException {
        empMapper.insertEmp(emp);
        if(10001==emp.getDeptId()){
            String pwd = MD5Util.getMD5String(MD5Util.getMD5String("123456"));
            Hr hr = Hr.builder().empId(emp.getEmpId()).empPass(pwd).build();
            hrMapper.insertHr(hr);
        }
    }
}
