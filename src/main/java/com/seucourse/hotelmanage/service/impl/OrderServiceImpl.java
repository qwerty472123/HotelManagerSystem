package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        Order order=Order.builder().userId(userId).build();
        return orderMapper.selectAllOrders(order);
    }

    @Override
    public void insertOrder(Order order) {
        orderMapper.insertOrder(order);
    }

}
