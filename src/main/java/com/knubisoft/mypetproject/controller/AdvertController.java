package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
