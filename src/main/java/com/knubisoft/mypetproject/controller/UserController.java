package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.service.UserService;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("user/all")
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "user_all";
    }
}
