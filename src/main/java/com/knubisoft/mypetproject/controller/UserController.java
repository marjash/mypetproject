package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.AdvertRepository;
import com.knubisoft.mypetproject.repository.CategoryRepository;
import com.knubisoft.mypetproject.repository.CityRepository;
import com.knubisoft.mypetproject.repository.UserRepository;
import com.knubisoft.mypetproject.service.CityServiceImpl;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Controller
//@RequestMapping("/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AdvertRepository advertRepository;


    @GetMapping("/")
    public String user(Model model) {
        User user = getUser();
        model.addAttribute("user", user);
        return "home";
    }

    @GetMapping("create/advert")
    public String createAdvert(Model model){
        Advert advert = new Advert();
        model.addAttribute("advert", advert);
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "create_advert";
    }

    @PostMapping("/create/advert")
    public String create(@Validated @ModelAttribute("advert") Advert advert) {
        User user = getUser();
        advert.setUser(user);
        advertRepository.save(advert);
        return "redirect:/advert/all";
    }

    @GetMapping("my/advert")
    public String getAdvert(Model model){
        User user = getUser();
        model.addAttribute("adverts", advertRepository.findAllByUserId(user.getId()));
        return "advert_all";
    }

    private User getUser() {
        User user = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().getClass().getName().equals(User.class.getName())) {
            if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("google")) {
                DefaultOidcUser google = (DefaultOidcUser) authentication.getPrincipal();
                String email = google.getEmail();
                user = userRepository.findByEmail(email);
            } else if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("facebook")) {
                String email = (String) (((OAuth2AuthenticationToken) authentication).getPrincipal()).getAttributes().get("email");
                user = userRepository.findByEmail(email);
            }
        }
        else
            user = (User) authentication.getPrincipal();
        return user;
    }
}
