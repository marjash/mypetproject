package com.knubisoft.mypetproject.controller;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.repository.AdvertRepository;
import com.knubisoft.mypetproject.repository.CategoryRepository;
import com.knubisoft.mypetproject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdvertController {

    @Autowired
    AdvertRepository advertRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CityRepository cityRepository;

    @GetMapping("/advert/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        advertRepository.deleteById(id);
        return "redirect:/search";
    }

    @RequestMapping(path = {"/", "/search"})
    public String home(Model model, String keyword, String categoryName, String cityName) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("cities", cityRepository.findAll());
        List<Advert> adverts = advertRepository.findAll();
        if (keyword != null && !keyword.isEmpty())
            adverts = advertRepository.findByAdvertName(keyword);
        if (categoryName != null && !categoryName.isEmpty())
            adverts = advertRepository.findByCategoryName(categoryName);
        if (categoryName != null && !cityName.isEmpty())
            adverts = advertRepository.findByCityId(cityName);
        model.addAttribute("adverts", adverts);
        return "advert_search";
    }
}
