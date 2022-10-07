package com.knubisoft.mypetproject.service;

import com.knubisoft.mypetproject.model.User;
import com.knubisoft.mypetproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        if (emailExists(user.getEmail()))
            throw new IllegalArgumentException("There is an account with that email address: "
                    + user.getEmail());
        userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public Optional<User> readById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
