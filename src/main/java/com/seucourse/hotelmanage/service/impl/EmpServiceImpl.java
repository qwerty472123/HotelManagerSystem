package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.Hr;
import com.seucourse.hotelmanage.mapper.EmpMapper;
import com.seucourse.hotelmanage.mapper.HrMapper;
import com.seucourse.hotelmanage.service.EmpService;
import com.seucourse.hotelmanage.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
