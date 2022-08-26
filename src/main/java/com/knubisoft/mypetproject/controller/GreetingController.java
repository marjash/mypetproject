package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

    @GetMapping("/reg/form")
    public String regForm(Model model) {
        model.addAttribute("person", new User());
        return "reg_form";
    }

    @PostMapping("/reg/form")
    public String regFormSubmit(@ModelAttribute User person, Model model){
        model.addAttribute("person", person);
        return "result";
    }


}