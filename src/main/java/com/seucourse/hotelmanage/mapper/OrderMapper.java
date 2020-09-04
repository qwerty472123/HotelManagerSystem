package com.seucourse.hotelmanage.mapper;


import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.provider.OrderSQLProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.jdbc.core.SqlProvider;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO order(roomId, userId, startDate, endDate, status) VALUES (#{roomId}, #{userId}, #{startDate}, #{endDate}, #{status})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertOrder(Order order);

    @SelectProvider(type = OrderSQLProvider.class, method = "createSelectSQL")
    List<Order> selectAllOrders(Order order);
}
