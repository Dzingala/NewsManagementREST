package com.epam.controller;

import by.epam.lab.task.entity.User;
import by.epam.lab.task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"},method = RequestMethod.GET)
    public String loginPage(ModelMap model){
        model.addAttribute("user", new User());
        return "login_index";
    }

    @RequestMapping(value = "/logout")
    public String logoutPage(ModelMap model){
        return "login_index";
    }
}
