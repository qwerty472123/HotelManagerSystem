package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.*;
import com.seucourse.hotelmanage.mapper.ConflictMapper;
import com.seucourse.hotelmanage.mapper.OrderMapper;
import com.seucourse.hotelmanage.service.OccupyService;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/front")
public class FrontController {


    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OccupyService occupyService;

    @GetMapping(path = "/showIn")
    public String showGoIn(Model model) {
        model.addAttribute("tab", 12);
        Order order=Order.builder().startDate(TimeUtil.getCurrentDate()).status(1).build();
        List<Order> orders=orderService.listOrder(order);
        model.addAttribute("orderList",orders);
        return "front_welcome";
    }

    @GetMapping(path = "/occupy/{orderId}")
    public String showOccupy(Model model, @PathVariable("orderId") Integer orderId) {
        model.addAttribute("tab",14);
        model.addAttribute("occupy", occupyService.listOccupy(Occupy.builder().orderId(orderId).build()));
        return "front_welcome";
    }

    @GetMapping(path = "/in/{orderId}")
    public String toIn(Model model, @PathVariable("orderId") Integer orderId) {
        model.addAttribute("tab",13);
        model.addAttribute("order", orderService.queryOrderByOrderId(orderId));
        return "front_welcome";
    }

    @PostMapping(path = "/in/{orderId}")
    @ResponseBody
    public String doIn(@PathVariable("orderId") Integer orderId,
                       @RequestParam(value = "name[]") String[] name,
                       @RequestParam(value = "certId[]") String[] certId) {
        if (orderService.updateStatus(orderId, 0) !=0 ) return "出现错误";
        int len = Math.min(name.length, certId.length);
        for(int i=0;i<len;i++){
            occupyService.addOccupy(Occupy.builder().orderId(orderId).name(name[i]).certId(certId[i]).build());
        }
        return "success";
    }

    @PostMapping(path = "/out")
    @ResponseBody
    public String doOut(Integer id) {
        List<Order> orders = orderService.listOrder(Order.builder().id(id).build());
        if (orders.size() != 1) {
            return "订单错误";
        }
        Order order = orders.get(0);
        if (order.getEndDate().after(TimeUtil.getCurrentDate())) {
            orderService.accOrder(order, TimeUtil.getCurrentDate());
        }
        orderService.updateStatus(id, 2);
        return "success";
    }

    @GetMapping(path = "/showOut")
    public String showOut(Model model) {
        model.addAttribute("tab", 2);
        Order order=Order.builder().endDate(TimeUtil.getCurrentDate()).status(0).build();
        List<Order> orders=orderService.listOrder(order);
        model.addAttribute("orderList",orders);
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
