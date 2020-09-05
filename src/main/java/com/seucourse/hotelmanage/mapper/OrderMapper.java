package com.seucourse.hotelmanage.mapper;


import com.seucourse.hotelmanage.entity.Conflict;
import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.provider.OrderSQLProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order` (roomId, userId, startDate, endDate, status) VALUES (#{roomId}, #{userId}, #{startDate}, #{endDate}, #{status})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertOrder(Order order);

    @SelectProvider(type = OrderSQLProvider.class, method = "createSelectSQL")
    @Results(id = "withRoomAndUser", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "roomId", property = "roomId"),
            @Result(column = "userId", property = "userId"),
            @Result(column = "startDate", property = "startDate"),
            @Result(column = "endDate", property = "endDate"),
            @Result(column = "status", property = "status"),
            @Result(column = "roomId", property = "room",
                    one = @One(select = "com.seucourse.hotelmanage.mapper.RoomMapper.selectRoomByRoomId",
                            fetchType = FetchType.LAZY)
            ),
            @Result(column = "userId", property = "user",
                    one = @One(select = "com.seucourse.hotelmanage.mapper.UserMapper.selectUserByUserId",
                            fetchType = FetchType.LAZY)
            )
    })
    List<Order> selectAllOrders(Order order);

    @Delete("DELETE FROM `order` WHERE id=#{orderId}")
    void deleteOrderByOrderId(Integer orderId);

    @Delete("DELETE FROM `order` WHERE userId=#{userId}")
    void deleteOrderByUserId(Integer userId);

    @UpdateProvider(type = OrderSQLProvider.class, method = "createUpdateSQL")
    void updateOrder(Order order);

    @Select("SELECT id, roomId, userId, startDate, endDate, status FROM `order` WHERE roomId = #{roomId} AND (#{date} BETWEEN startDate AND endDate)")
    Order selectOrderByConflict(Conflict conflict);
}
