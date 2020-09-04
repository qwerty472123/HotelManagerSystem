package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Conflict;
import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.mapper.ConflictMapper;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ConflictMapper conflictMapper;

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        Order order=Order.builder().userId(userId).build();
        return orderMapper.selectAllOrders(order);
    }

    @Override
    public void insertOrder(Order order) {
        for(long time = order.getStartDate().getTime(); time <= order.getEndDate().getTime(); time += 3600 * 1000 * 24) {
            conflictMapper.insertConflict(Conflict.builder().roomId(order.getRoomId()).date(new Date(time)).build());
        }
        orderMapper.insertOrder(order);
    }

}
