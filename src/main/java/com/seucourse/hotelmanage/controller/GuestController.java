package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import com.seucourse.hotelmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/guest")
public class GuestController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "register")
    public String preRegister() {
        return "guest_register";
    }
    @PostMapping(path = "register")
    public String doRegister(User user, Model model) {
        System.out.println("注册用户 "+user);
        user.setRole(0);
        Integer status = userService.register(user);
        System.out.println("注册验证结果 "+status);
        if (status == 0) {
            return "redirect:/";
        }
        model.addAttribute("errorId", status);
        return "guest_register";
    }

    @GetMapping(path="/")
    public String welcome(Model model){
        model.addAttribute("tab",0);
        return "guest_welcome";
    }

    @GetMapping(path = "/order")
    public String orderRoom(Model model){
        model.addAttribute("tab",1);
        model.addAttribute("roomTypes",roomService.listRoomTypes());
        return "guest_welcome";
    }

    @PostMapping(path = "/addOrder")
    public @ResponseBody String addOrder(Model model, String roomType, Date startDate, Date endDate){
        Room room = roomService.getRoomByTypeAndTime(roomType,startDate,endDate);
        System.out.println("预约 type " + roomType);
        String msg="success";
        if(null == room){
            msg="所选时间内无此类型的可用房间!";
            return msg;
        }
        Integer userId = ((User)model.getAttribute("curUser")).getId();
        Order order = Order.builder().userId(userId)
                .roomId(room.getId())
                .startDate(startDate)
                .endDate(endDate)
                .status(1)
                .build();
        orderService.insertOrder(order);

        return msg;
    }

    @GetMapping(path = "/removeOrder/{orderId}")
    public @ResponseBody String removeOrder(@PathVariable("orderId") Integer orderId){
        System.out.println("删除记录"+orderId);

        String msg=orderService.deleteOrderByOrderId(orderId);
        return msg;
    }



    @GetMapping(path = "/showList")
    public String showRecList(Model model){
        User user = (User)model.getAttribute("curUser");
        model.addAttribute("tab",2);
        List<Order> orders = orderService.queryOrdersByUserId(user.getId());
        model.addAttribute("orderList",orders);
        return "guest_welcome";
    }



}
