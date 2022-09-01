package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import com.knubisoft.mypetproject.security.RegistrationForm;
import com.knubisoft.mypetproject.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    CityServiceImpl cityService;

    @GetMapping
    public String registerForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("cities", cityService.getAll());
        return "registration";
    }

    @PostMapping
    private String processRegistration(RegistrationForm form){
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

    public RegistrationController(
            UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
