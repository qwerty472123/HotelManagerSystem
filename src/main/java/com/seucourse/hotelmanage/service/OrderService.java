package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> queryOrdersByUserId(Integer userId);
    void insertOrder(Order order);
}
