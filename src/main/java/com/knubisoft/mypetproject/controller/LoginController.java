package com.knubisoft.mypetproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String login(@RequestParam(value = "error", defaultValue = "false") boolean loginError) {
        if (loginError) {
            System.out.println("error");
        }
        return "login";
    }
}