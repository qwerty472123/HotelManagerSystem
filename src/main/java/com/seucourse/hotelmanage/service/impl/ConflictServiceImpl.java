package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Conflict;
import com.seucourse.hotelmanage.entity.EChartInfo;
import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.mapper.ConflictMapper;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.mapper.RoomMapper;
import com.seucourse.hotelmanage.service.ConflictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.*;

@Service("conflictService")
public class ConflictServiceImpl implements ConflictService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ConflictMapper conflictMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public EChartInfo getEChartInfo(Date startDate, Date endDate) {
        List<String> rooms = new ArrayList<>();
        List<Room> roomDB = roomMapper.selectRoomInTime(startDate, endDate);
        Map<Integer, Integer> roomMap = new HashMap<>();
        Integer id = 0;
        for (Room room : roomDB) {
            rooms.add(room.getName());
            roomMap.put(room.getId(), id);
            id++;
        }
        List<String> days = new ArrayList<>();
        Map<Date, Integer> dateMap = new HashMap<>();
        id = 0;
        for (long time = startDate.getTime(); time <= endDate.getTime();
             time += 24 * 3600 * 1000) {
            Date cur = new Date(time);
            dateMap.put(cur, id);
            id++;
            days.add(DateFormat.getDateInstance(3).format(cur));
        }
        List<Conflict> conflicts = conflictMapper.selectConflicts(startDate, endDate);
        List<List<Integer>> data = new ArrayList<>();
        Integer maxCost = 1;
        for (Conflict conflict : conflicts) {
            List<Integer> tuple = new ArrayList<>();
            tuple.add(roomMap.get(conflict.getRoomId()));
            tuple.add(dateMap.get(conflict.getDate()));
            Order order = orderMapper.selectOrderByConflict(conflict);
            if (order == null) {
                tuple.add(1);
            } else {
                Integer cost = Math.toIntExact((order.getEndDate().getTime() - order.getStartDate().getTime()) / (3600 * 24 * 1000) + 1);
                tuple.add(cost);
                maxCost = Math.max(maxCost, cost);
            }
            data.add(tuple);
        }
        return EChartInfo.builder().days(days).rooms(rooms).data(data).max(maxCost).build();
    }
}
