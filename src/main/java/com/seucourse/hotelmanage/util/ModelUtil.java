package com.seucourse.hotelmanage.util;

import com.seucourse.hotelmanage.entity.User;
import org.springframework.ui.Model;

public class ModelUtil {
    public static User getUserFromModel(Model model) {
        User user = (User) model.getAttribute("curUser");
        if (user == null) return new User();
        return user;
    }
}
