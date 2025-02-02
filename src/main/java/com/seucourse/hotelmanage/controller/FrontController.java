package com.seucourse.hotelmanage.controller;

import com.seucourse.hotelmanage.entity.Occupy;
import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.service.OccupyService;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.RoomService;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.ModelUtil;
import com.seucourse.hotelmanage.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/front")
public class FrontController {
    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    OccupyService occupyService;

    @Autowired
    RoomService roomService;

    @GetMapping(path = "/order")
    public String orderRoom(Model model) {
        model.addAttribute("tab", 1);
        model.addAttribute("roomTypes", roomService.listRoomTypes());
        return "front_operation";
    }

    @PostMapping(path = "/addOrder")
    public @ResponseBody
    String addOrder(Model model, String roomType, Date startDate, Date endDate) {
        if (TimeUtil.getCurrentDate().after(startDate)) return "预约时间已过";
        if (startDate.after(endDate)) return "退房日期不能早于入住日期";
        Room room = roomService.getRoomByTypeAndTime(roomType, startDate, endDate);
        System.out.println("预约 type " + roomType);
        String msg = "success";
        if (null == room) {
            msg = "所选时间内无此类型的可用房间!";
            return msg;
        }
        Integer userId = ModelUtil.getUserFromModel(model).getId();
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
    public @ResponseBody
    String removeOrder(@PathVariable("orderId") Integer orderId) {
        System.out.println("删除记录" + orderId);

        return orderService.deleteOrderByOrderId(orderId);
    }


    @GetMapping(path = "/extendOrder/{orderId}")
    public String extendOrder(Model model, @PathVariable("orderId") Integer orderId) {
        System.out.println("续约" + orderId);
        Order order = orderService.queryOrderByOrderId(orderId);
        System.out.println("查询结束");
        //System.out.println(order);
        model.addAttribute("order", order);
        model.addAttribute("tab", 3);
        return "front_operation";
    }

    @PostMapping(path = "/extendOrder")
    public @ResponseBody
    String postExtendOrder(Model model, Integer orderId, Date endDate) {
        System.out.println("续约" + orderId + "endDate=");
        System.out.println(endDate);

        Order order = orderService.queryOrderByOrderId(orderId);
        if (order.getEndDate().after(endDate)) return "只能延后";
        Date d1 = new Date(order.getEndDate().getTime() + 3600 * 1000 * 24);

        Room room = roomService.getRoomByCheckTime(order.getRoomId(), d1, endDate);

        String msg = "success";
        if (null == room) {
            msg = "续约失败，未来日期冲突。";
            return msg;
        }

        order.setEndDate(endDate);
        orderService.updateOrder(order, d1, endDate);
        return msg;
    }

    @GetMapping(path = "/showIn")
    public String showGoIn(Model model) {
        model.addAttribute("tab", 12);
        Order order = Order.builder().startDate(TimeUtil.getCurrentDate()).status(1).build();
        List<Order> orders = orderService.listOrder(order);
        model.addAttribute("orderList", orders);
        return "front_operation";
    }

    @GetMapping(path = "/occupy/{orderId}")
    public String showOccupy(Model model, @PathVariable("orderId") Integer orderId) {
        model.addAttribute("tab", 14);
        model.addAttribute("occupy", occupyService.listOccupy(Occupy.builder().orderId(orderId).build()));
        return "front_operation";
    }

    @GetMapping(path = "/in/{orderId}")
    public String toIn(Model model, @PathVariable("orderId") Integer orderId) {
        model.addAttribute("tab", 13);
        model.addAttribute("order", orderService.queryOrderByOrderId(orderId));
        return "front_operation";
    }

    @PostMapping(path = "/in/{orderId}")
    @ResponseBody
    public String doIn(@PathVariable("orderId") Integer orderId,
                       @RequestParam(value = "name[]") String[] name,
                       @RequestParam(value = "certId[]") String[] certId) {
        if (orderService.updateStatus(orderId, 0) != 0) return "出现错误";
        int len = Math.min(name.length, certId.length);
        for (int i = 0; i < len; i++) {
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
        roomService.updateRoom(Room.builder().id(order.getRoomId()).clean(0).build());
        return "success";
    }

    @GetMapping(path = "/showOut")
    public String showOut(Model model) {
        model.addAttribute("tab", 2);
        Order order = Order.builder().endDate(TimeUtil.getCurrentDate()).status(0).build();
        List<Order> orders = orderService.listOrder(order);
        model.addAttribute("orderList", orders);
        return "front_operation";
    }

    @GetMapping(path = "/")
    public String showRecList(Model model) {
        model.addAttribute("tab", 2);

        Order order = Order.builder().build();
        List<Order> orders = orderService.listOrder(order);

        model.addAttribute("orderList", orders);

        return "front_operation";
    }

    @PostMapping("/changePwd")
    public @ResponseBody
    String changePwd(Integer userId, String password) {
        System.out.println("修改密码 " + userId + " " + password);
        User user = User.builder().id(userId).password(password).build();
        return userService.updateUser(user);
    }

    @PostMapping("/deleteUser")
    public @ResponseBody
    String deleteUser(Integer userId) {
        return userService.deleteUserByUserId(userId);
    }

    @GetMapping(path = "/userManage")
    public String showUserList(Model model) {

        model.addAttribute("tab", 4);

        User user = User.builder().role(0).build();
        List<User> users = userService.listUsers(user);

        model.addAttribute("userList", users);

        return "front_operation";
    }

    @GetMapping(path = "/addRoom")
    public String toAddRoom(Model model) {
        model.addAttribute("tab", 23);
        return "front_operation";
    }

    @PostMapping(path = "/addRoom")
    @ResponseBody
    public String doAddRoom(Model model, Room room) {
        room.setClean(1);
        roomService.addRoom(room);
        return "success";
    }

    @GetMapping(path = "/showRoom")
    public String showRooms(Model model) {
        model.addAttribute("tab", 22);
        model.addAttribute("rooms", roomService.listRoom(Room.builder().build()));
        return "front_operation";
    }

    @PostMapping(path = "/bj")
    @ResponseBody
    public String requireBJ(Integer id) {
        List<Room> rooms = roomService.listRoom(Room.builder().id(id).build());
        if (rooms.size() != 1) return "无此房间";
        Room room = rooms.get(0);
        roomService.updateRoom(Room.builder().id(room.getId()).clean(0).build());
        return "成功";
    }

    @PostMapping(path = "/deleteRoom")
    @ResponseBody
    public String deleteRoom(Integer id) {
        List<Room> rooms = roomService.listRoom(Room.builder().id(id).build());
        if (rooms.size() != 1) return "无此房间";
        Room room = rooms.get(0);
        roomService.deleteRoom(room.getId());
        return "成功";
    }

    @GetMapping(path = "/roomState")
    public String showRoomState(Model model) {
        model.addAttribute("tab", 0);
        return "front_operation";
    }
}
