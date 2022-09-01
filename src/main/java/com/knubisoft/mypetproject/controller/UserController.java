package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import com.knubisoft.mypetproject.service.CityServiceImpl;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
//@RequestMapping("/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public String user(Model model) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "home";
    }


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
