package com.myexample.bootwithmybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping(path = "/")
    public String welcome(){
        return "login";
    }
}
