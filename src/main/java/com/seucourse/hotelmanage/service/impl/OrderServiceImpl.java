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
    public List<Order> listOrder(Order order) {
        return orderMapper.selectAllOrders(order);
    }

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

    @Override
    public String deleteOrderByOrderId(Integer orderId) {
        String msg = "该预约已生效或过期，无法取消预约";
        Order order=Order.builder().id(orderId).build();
        List<Order> orders = orderMapper.selectAllOrders(order);
        order=orders.get(0);
        if(order.getStatus()!=1){
            return msg;
        }
        for(long time = order.getStartDate().getTime(); time <= order.getEndDate().getTime(); time += 3600 * 1000 * 24) {
            conflictMapper.deleteConflict(Conflict.builder().roomId(order.getRoomId()).date(new Date(time)).build());
        }
        orderMapper.deleteOrderByOrderId(orderId);
        msg="success";
        return msg;
    }


}
