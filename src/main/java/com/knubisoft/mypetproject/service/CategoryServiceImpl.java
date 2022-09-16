package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{


    @Override
    public Optional<Category> readById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Category> getAll() {
        return null;
    }
}
