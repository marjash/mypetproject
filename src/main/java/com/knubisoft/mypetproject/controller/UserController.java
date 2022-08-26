package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.service.CityServiceImpl;
import com.knubisoft.mypetproject.service.UserService;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;
    CityServiceImpl cityService;


    @GetMapping("/reg/form")
    public String regForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("cities", cityService.getAll());
        return "reg_form";
    }

    @PostMapping("/reg/form")
    public String regFormSubmit(@ModelAttribute User user, Model model){
        model.addAttribute("user", user);
        return "result";
    }


    @GetMapping("user/all")
    public String getAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "user_all";
    }
}
