package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.repository.AdvertRepository;
import com.knubisoft.mypetproject.service.AdvertServiceImpl;
import com.knubisoft.mypetproject.model.Advert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepository advertRepository;


    @GetMapping("advert/all")
    public String getAll(Model model) {
        model.addAttribute("adverts", advertRepository.findAll());
        return "advert_all";
    }

    @GetMapping("/advert/{id}/delete")
    public String delete(@PathVariable("id") long id){
        advertRepository.deleteById(id);
        return "redirect:/advert/all";
    }
}
