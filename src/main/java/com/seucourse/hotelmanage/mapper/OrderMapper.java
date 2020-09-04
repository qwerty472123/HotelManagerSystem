package com.seucourse.hotelmanage.mapper;


import com.seucourse.hotelmanage.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO order(roomId, userId, startDate, endDate, status) VALUES (#{roomId}, #{userId}, #{startDate}, #{endDate}, #{status})")
    void insertOrder(Order order);
}
