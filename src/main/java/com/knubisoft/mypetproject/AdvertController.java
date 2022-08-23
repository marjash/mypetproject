package com.knubisoft.mypetproject;

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
    AdvertServiceImpl advertService;

    @GetMapping("/create/advert")
    public String addAdvert(Model model){
        model.addAttribute("advert", new Advert());
        return "create_advert";
    }

    @PostMapping("/create/advert")
    public String create(@Validated @ModelAttribute("advert") Advert advert){
        advertService.save(advert);
        return "redirect:/advert/all";
    }

    @GetMapping("advert/all")
    public String getAll(Model model) {
        model.addAttribute("adverts", advertService.getAll());
        return "advert_all";
    }

    @GetMapping("/advert/{id}/delete")
    public String delete(@PathVariable("id") long id){
        advertService.delete(id);
        return "redirect:/advert/all";
    }

}
