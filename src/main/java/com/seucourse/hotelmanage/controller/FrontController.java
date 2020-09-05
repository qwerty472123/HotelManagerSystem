package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.TimeUtil;
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

    @Autowired
    UserService userService;

    @GetMapping(path = "/showIn")
    public String showGoIn(Model model) {
        model.addAttribute("tab", 12);
        Order order=Order.builder().startDate(TimeUtil.getCurrentDate()).status(1).build();
        List<Order> orders=orderService.listOrder(order);
        return "front_welcome";
    }

    @GetMapping(path = "/")
    public String showRecList(Model model) {

        model.addAttribute("tab", 2);

        Order order=Order.builder().build();
        List<Order> orders=orderService.listOrder(order);

        model.addAttribute("orderList",orders);

        return "front_welcome";
    }

    @GetMapping(path="/userManage")
    public String showUserList(Model model){

        model.addAttribute("tab",4);

        User user=User.builder().build();
        List<User> users=userService.listUsers(user);

        model.addAttribute("userList",users);

        return "front_welcome";
    }

}
