package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path="/front")
public class FrontController {


    @Autowired
    OrderService orderService;

    @GetMapping(path = "/")
    public String showRecList(Model model) {
        Room room = Room.builder().build();


        model.addAttribute("tab", 2);

        Order order=Order.builder().build();
        List<Order> orders=orderService.listOrder(order);

        model.addAttribute("orderList",orders);

        return "front_welcome";
    }

}
