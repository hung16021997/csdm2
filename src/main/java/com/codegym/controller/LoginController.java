package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/admin")
    public String login(){
        return "admin";
    }

    @GetMapping("/user")
    public String loginUser(){
        return "user";
    }

    @GetMapping("/index")
    public String loginHomepage(){
        return "index";
    }
}