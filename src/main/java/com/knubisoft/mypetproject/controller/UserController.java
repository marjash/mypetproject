package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.AdvertRepository;
import com.knubisoft.mypetproject.repository.CategoryRepository;
import com.knubisoft.mypetproject.repository.CityRepository;
import com.knubisoft.mypetproject.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Controller
//@RequestMapping("/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

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
    public String createAdvert(Model model) {
        Advert advert = new Advert();
        model.addAttribute("advert", advert);
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("categories", categoryRepository.findAll());
        return "create_advert";
    }

    @SneakyThrows
    @PostMapping("/create/advert")
    public String create (@Validated @ModelAttribute("advert") Advert advert,
                          @RequestParam("image") List<MultipartFile> multipartFile) {
        User user = getUser();
        advert.setUser(user);
        advert.setDateOfCreation(LocalDate.now());
        String uploadDir = null;
        for (MultipartFile m : multipartFile) {
            String fileName = StringUtils.cleanPath(m.getOriginalFilename());
            uploadDir = "/images/" + user.getId() + "/" + LocalDateTime.now();
            Path path = Paths.get("/mypetproject/images/" + user.getId()+ "/" + LocalDateTime.now());
            if (!Files.exists(path))
                Files.createDirectories(path);
            InputStream inputStream = m.getInputStream();
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        advert.setImagePath(uploadDir);
        advertRepository.save(advert);
        return "redirect:/my/advert";
    }

    @GetMapping("my/advert")
    public String getAdvert(Model model) {
        User user = getUser();
        model.addAttribute("adverts", advertRepository.findAllByUserId(user.getId()));
        return "my_advert";
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
        } else
            user = (User) authentication.getPrincipal();
        return user;
    }

}
