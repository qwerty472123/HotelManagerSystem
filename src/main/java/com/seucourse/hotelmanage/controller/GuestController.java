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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @GetMapping(path = "/addOrder")
    public @ResponseBody String addOrder(Model model, String roomType, Date startDate, Date endDate){

        Room room = roomService.getRoomByTypeAndTime(roomType,startDate,endDate);
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
                .build();
        orderService.insertOrder(order);

        //TODO 从startDate到endDate添加conflict记录

        return msg;
    }




    @GetMapping(path = "/showList")
    public String showRecList(Model model){





        model.addAttribute("tab",2);








        return "guest_welcome";
    }



}
