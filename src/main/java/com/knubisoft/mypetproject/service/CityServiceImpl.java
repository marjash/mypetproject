package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.City;
import com.knubisoft.mypetproject.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Optional<City> readById(long id) {
        return cityRepository.findById(id);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }
}
