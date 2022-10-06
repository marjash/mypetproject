package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import com.knubisoft.mypetproject.security.RegistrationForm;
import com.knubisoft.mypetproject.service.CityServiceImpl;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CityServiceImpl cityService;

    @GetMapping
    public String registerForm(@ModelAttribute("user") User user, Model model){
        model.addAttribute("cities", cityService.getAll());
        return "registration";
    }

    @PostMapping
    private String processRegistration(@ModelAttribute("user") @Valid RegistrationForm user, BindingResult bindingResult,
                                       Model model){
        if (bindingResult.hasErrors()) {
            model.addAttribute("cities", cityService.getAll());
            return "registration";
        }
        user.setDateOfRegistration(LocalDate.now());
        userService.save(user.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
