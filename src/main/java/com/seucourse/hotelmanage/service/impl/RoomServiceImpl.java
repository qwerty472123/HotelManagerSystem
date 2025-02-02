package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.mapper.RoomMapper;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("roomService")
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public void addRoom(Room room) {
        roomMapper.insertRoom(room);
    }

    @Override
    public List<Room> listRoom(Room room) {
        if (room == null) {
            room = Room.builder().build();
        }
        return roomMapper.selectRoom(room);
    }

    @Override
    public void updateRoom(Room room) {
        roomMapper.updateRoom(room);
    }

    @Override
    public List<String> listRoomTypes() {
        return roomMapper.selectTypes();
    }

    @Override
    public Room getRoomByTypeAndTime(String type, Date startDate, Date endDate) {
        return roomMapper.selectRoomByTypeAndTime(type, startDate, endDate);
    }

    @Override
    public Room getRoomByCheckTime(Integer id, Date checkStart, Date checkEnd) {
        return roomMapper.selectRoomByCheckTime(id, checkStart, checkEnd);
    }

    @Override
    public void deleteRoom(Integer roomId) {
        List<Order> orders = orderService.listOrder(Order.builder().roomId(roomId).build());
        for (Order order : orders) {
            orderService.deleteOrderByOrderIdForce(order.getId());
        }
        roomMapper.deleteRoom(roomId);
    }
}
