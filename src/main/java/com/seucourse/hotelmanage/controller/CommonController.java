package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.EChartInfo;
import com.seucourse.hotelmanage.service.ConflictService;
import com.seucourse.hotelmanage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(path = "/common")
public class CommonController {
    @Autowired
    private ConflictService conflictService;

    @GetMapping(path = "/echart")
    @ResponseBody
    public EChartInfo getEChartInfo() {
        return conflictService.getEChartInfo(TimeUtil.getCurrentDate(),
                new Date(TimeUtil.getCurrentDate().getTime() + 7 * 1000 * 3600 * 24));
    }
}
