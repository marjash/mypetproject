package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.City;

import java.util.List;
import java.util.Optional;

public interface CityService {
    Optional<City> readById(long id);
    List<City> getAll();
}
