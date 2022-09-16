package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.Category;
import com.knubisoft.mypetproject.model.City;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> readById(long id);
    List<Category> getAll();
}
