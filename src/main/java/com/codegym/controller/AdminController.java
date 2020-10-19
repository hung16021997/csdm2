package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    //home
    @GetMapping("/admin/home")
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }
}
