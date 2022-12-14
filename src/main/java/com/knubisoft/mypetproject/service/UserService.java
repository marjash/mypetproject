package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> readById(long id);
    List<User> getAll();

    User findByEmail(String email);

}
