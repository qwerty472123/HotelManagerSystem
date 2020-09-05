package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/back")
public class BackController {
    @Autowired
    private RoomService roomService;

    @GetMapping(path = "/")
    public String showRecList(Model model) {
        Room room = Room.builder().build();
        model.addAttribute("tab", 1);
        List<Room> rooms = roomService.listRoom(room);
        model.addAttribute("rooms", rooms);
        return "back_welcome";
    }
    @GetMapping(path = "/only_required")
    public String showRoomOnly(Model model) {
        Room room = Room.builder().clean(0).build();
        model.addAttribute("tab", 1);
        List<Room> rooms = roomService.listRoom(room);
        model.addAttribute("rooms", rooms);
        return "back_welcome";
    }
    @GetMapping(path = "/setClean/{roomId}")
    public @ResponseBody
    String setRoomInfo(@PathVariable("roomId") Integer roomId){
        try{
            roomService.updateRoom(Room.builder().id(roomId).clean(1).build());
            return "success";
        } catch (Exception err) {
            err.printStackTrace();
            return "failed";
        }
    }
}
