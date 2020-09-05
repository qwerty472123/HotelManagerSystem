package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.EChartInfo;

import java.util.Date;

public interface ConflictService {
    EChartInfo getEChartInfo(Date startDate, Date endDate);
}
