package com.seucourse.hotelmanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorController {
    @GetMapping("/error")
    @ResponseBody
    public String showErrorPage() {
        return "This is a error page!";
    }
}
