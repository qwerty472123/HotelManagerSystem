package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.util.TimeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(path = "/dev")
public class DevController {
    @GetMapping(path = "/time")
    @ResponseBody
    public Date getTime() {
        return TimeUtil.getCurrentDate();
    }
    @PostMapping(path = "/time")
    @ResponseBody
    public Date setTime(Integer delta) {
        TimeUtil.changeDate(delta);
        return TimeUtil.getCurrentDate();
    }
}
