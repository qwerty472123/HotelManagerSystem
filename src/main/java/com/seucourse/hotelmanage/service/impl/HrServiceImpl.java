package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Hr;
import com.seucourse.hotelmanage.mapper.HrMapper;
import com.seucourse.hotelmanage.service.HrService;
import com.seucourse.hotelmanage.util.MD5Util;
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
