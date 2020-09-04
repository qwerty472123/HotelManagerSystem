package com.myexample.bootwithmybatis.service.impl;

import com.myexample.bootwithmybatis.entity.Hr;
import com.myexample.bootwithmybatis.mapper.HrMapper;
import com.myexample.bootwithmybatis.service.HrService;
import com.myexample.bootwithmybatis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class HrServiceImpl implements HrService {
    @Autowired
    private HrMapper hrMapper;
    @Override
    public Integer login(Hr hr) throws NoSuchAlgorithmException {
        Hr hrDB = hrMapper.selectHrByEmpId(hr.getEmpId());
        if(hrDB==null)return 2;
        String pwd = MD5Util.getMD5String(MD5Util.getMD5String(hr.getEmpPass()));
        if(pwd.equals(hrDB.getEmpPass())) return 1;
        return 0;
    }
}
