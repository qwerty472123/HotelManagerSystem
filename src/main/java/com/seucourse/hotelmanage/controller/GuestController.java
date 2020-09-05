package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.entity.User;
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
        if(TimeUtil.getCurrentDate().after(startDate)) return "预约时间已过";
        if (startDate.after(endDate)) return "退房日期不能早于入住日期";
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
    public @ResponseBody String removeOrder(@PathVariable("orderId") Integer orderId, Model model){
        System.out.println("删除记录"+orderId);
        Order order=orderService.queryOrderByOrderId(orderId);
        if(order.getUserId() != ((User)model.getAttribute("curUser")).getId()) return "其他用户的订单";
        String msg=orderService.deleteOrderByOrderId(orderId);
        return msg;
    }


    @GetMapping(path = "/extendOrder/{orderId}")
    public String extendOrder(Model model,@PathVariable("orderId") Integer orderId){
        System.out.println("续约"+orderId);
        Order order=orderService.queryOrderByOrderId(orderId);
        if(order.getUserId() != ((User)model.getAttribute("curUser")).getId()) return "其他用户的订单";
        System.out.println("查询结束");
        //System.out.println(order);
        model.addAttribute("order",order);
        model.addAttribute("tab",3);
        return "guest_welcome";
    }

    @PostMapping(path = "/extendOrder")
    public @ResponseBody String postExtendOrder(Model model,Integer orderId,Date endDate){
        System.out.println("续约"+orderId+"endDate=");
        System.out.println(endDate);

        Order order=orderService.queryOrderByOrderId(orderId);
        if(order.getUserId() != ((User)model.getAttribute("curUser")).getId()) return "其他用户的订单";
        if (order.getEndDate().after(endDate)) return "只能延后";
        Date d1 = new Date(order.getEndDate().getTime()+3600*1000*24);
        Date d2=endDate;

        Room room=roomService.getRoomByCheckTime(order.getRoomId(),d1,d2);

        String msg="success";
        if(null == room){
            msg="续约失败，未来日期冲突。";
            return msg;
        }

        order.setEndDate(d2);
        orderService.updateOrder(order,d1,d2);
        return "success";
    }


    @GetMapping(path = "/showList")
    public String showRecList(Model model){
        User user = (User)model.getAttribute("curUser");
        model.addAttribute("tab",2);
        List<Order> orders = orderService.queryOrdersByUserId(user.getId());
        model.addAttribute("orderList",orders);
        return "guest_welcome";
    }

    @PostMapping(path = "/bj")
    @ResponseBody
    public String requireBJ(Integer id) {
        List<Order> orders = orderService.listOrder(Order.builder().id(id).build());
        if (orders.size() != 1) return "无订单";
        Order order = orders.get(0);
        if (order.getStatus() != 0) return "未入住";
        roomService.updateRoom(Room.builder().id(order.getRoomId()).clean(0).build());
        return "成功";
    }
}
