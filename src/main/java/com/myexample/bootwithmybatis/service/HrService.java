package com.myexample.bootwithmybatis.service;

import com.myexample.bootwithmybatis.entity.Hr;

import java.security.NoSuchAlgorithmException;

public interface HrService {
    /**
     * Login
     * @param hr the hr
     * @return result
     *          0. pwd error
     *          1. success
     *          2. not found
     */
    Integer login(Hr hr) throws NoSuchAlgorithmException;
}
