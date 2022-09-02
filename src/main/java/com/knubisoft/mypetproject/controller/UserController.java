package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
//@RequestMapping("/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/")
    public String user(Model model) {
        User user = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("google")){
            DefaultOidcUser google = (DefaultOidcUser) authentication.getPrincipal();
            String email = google.getEmail();
            user = userRepository.findByEmail(email);
        }
        else if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("facebook")){
            String email = (String) (((OAuth2AuthenticationToken) authentication).getPrincipal()).getAttributes().get("email");
            user = userRepository.findByEmail(email);
        }
        else {
            user = (User) authentication.getPrincipal();
        }

        model.addAttribute("user", user);
        return "home";
    }


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
