package com.seucourse.hotelmanage.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class CommonController {
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(true);
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
    @ModelAttribute
    public void populateUserName(HttpServletRequest request, Model model) {
        model.addAttribute("curUser", request.getAttribute("curUser"));
    }

}
