package com.seucourse.hotelmanage.mapper;


import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.provider.OrderSQLProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.jdbc.core.SqlProvider;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO order(roomId, userId, startDate, endDate, status) VALUES (#{roomId}, #{userId}, #{startDate}, #{endDate}, #{status})")
    void insertOrder(Order order);

    @SelectProvider(type = OrderSQLProvider.class, method = "createSelectSQL")
    List<Order> selectAllOrders(Order order);
}
