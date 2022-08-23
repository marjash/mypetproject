package com.knubisoft.mypetproject;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class GreetingController {

    @GetMapping("/reg/form")
    public String regForm(Model model) {
        model.addAttribute("person", new Person());
        return "reg_form";
    }

    @PostMapping("/reg/form")
    public String regFormSubmit(@ModelAttribute Person person, Model model){
        model.addAttribute("person", person);
        return "result";
    }
}