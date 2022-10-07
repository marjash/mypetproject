package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.AdvertRepository;
import com.knubisoft.mypetproject.repository.CategoryRepository;
import com.knubisoft.mypetproject.repository.CityRepository;
import com.knubisoft.mypetproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/advert/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        advertRepository.deleteById(id);
        return "redirect:/search";
    }

    @GetMapping("/advert/{id}/book/cancel")
    public String cancelBook(@PathVariable("id") long id) {
        advertRepository.cancelBook(id);
        return "redirect:/advert/{id}";
    }

    @GetMapping("/advert/{id}")
    public String getAdvert(@PathVariable("id") long id, Model model) {
        Optional<Advert> byId = advertRepository.findById(id);
        Long bookUser = null;
        User user = getUser();
        if (advertRepository.getBookUser(id) != null)
           bookUser  = advertRepository.getBookUser(id);
        model.addAttribute("advert", byId);
        model.addAttribute("bookUser", bookUser);
        model.addAttribute("user", user);
        return "advert_info";
    }

    @RequestMapping(path = {"/", "/search"})
    public String allAdverts(Model model, String keyword, String categoryName, String cityName) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        List<Advert> adverts = advertRepository.findAllAdverts();
        if (keyword != null && !keyword.isEmpty())
            adverts = advertRepository.findByAdvertName(keyword);
        if (categoryName != null && !categoryName.isEmpty())
            adverts = advertRepository.findByCategoryName(categoryName);
        if (categoryName != null && !cityName.isEmpty())
            adverts = advertRepository.findByCityId(cityName);
        model.addAttribute("adverts", adverts);
        return "advert_search";
    }

    private User getUser() {
        User user = null;
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser"))
            return null;
        if (!authentication.getPrincipal().getClass().getName().equals(User.class.getName())) {
            if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("google")) {
                DefaultOidcUser google = (DefaultOidcUser) authentication.getPrincipal();
                String email = google.getEmail();
                user = userService.findByEmail(email);
            } else if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("facebook")) {
                String email = (String) (((OAuth2AuthenticationToken) authentication).getPrincipal()).getAttributes().get("email");
                user = userService.findByEmail(email);
            }
        } else
            user = (User) authentication.getPrincipal();
        return user;
    }

}
