package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> listOrder(Order order);
    List<Order> queryOrdersByUserId(Integer userId);
    void insertOrder(Order order);
    String deleteOrderByOrderId(Integer orderId);
    Order queryOrderByOrderId(Integer orderId);
    void updateOrder(Order order);
}
