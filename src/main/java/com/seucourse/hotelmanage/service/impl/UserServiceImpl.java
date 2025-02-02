package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.Order;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.mapper.UserMapper;
import com.seucourse.hotelmanage.service.OrderService;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.PasswordEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderService orderService;

    @Override
    public Integer login(User user) {
        User userInDB = userMapper.selectUser(User.builder().username(user.getUsername()).build());
        if (userInDB == null) return -1; // no such user
        if (!PasswordEncryptUtil.check(user.getPassword(), userInDB.getPassword())) return -2; // error pwd
        user.setRole(userInDB.getRole());
        user.setId(userInDB.getId());
        user.setName(userInDB.getName());
        return 0;
    }

    /**
     * register
     *
     * @param user role must set by server
     * @return status code
     */
    @Override
    public Integer register(User user) {
        User tmp = userMapper.selectUser(User.builder().username(user.getUsername()).build());
        if (tmp != null) return -1; // has same name
        if (user.getName() == null || user.getName().length() == 0) return -2; // No name
        if (user.getUsername() == null || user.getUsername().length() < 3) return -3; // Too short username
        if (user.getPassword() == null || user.getPassword().length() < 3) return -4; // Too short password
        user.setPassword(PasswordEncryptUtil.encrypt(user.getPassword()));
        userMapper.insertUser(user);
        return 0;
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectUser(User.builder().id(userId).build());
    }

    @Override
    public String updateUser(User user) {
        if (user.getName() != null && user.getName().length() == 0) return "姓名不能为空";
        if (user.getUsername() != null && user.getUsername().length() < 3) return "用户名太短";
        if (null != user.getPassword() && !user.getPassword().equals("")) {
            if (user.getPassword().length() < 3) return "密码太短";
            user.setPassword(PasswordEncryptUtil.encrypt(user.getPassword()));
        }
        userMapper.updateUser(user);
        return "success";
    }

    @Override
    public String deleteUserByUserId(Integer userId) {
        List<Order> orders = orderService.listOrder(Order.builder().userId(userId).build());
        for (Order order : orders) {
            orderService.deleteOrderByOrderIdForce(order.getId());
        }
        userMapper.deleteUser(userId);
        return "success";
    }

    @Override
    public List<User> listUsers(User user) {
        return userMapper.selectUsers(user);
    }
}
