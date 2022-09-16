package com.knubisoft.mypetproject.security;

import com.knubisoft.mypetproject.controller.UserController;
import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.City;
import com.knubisoft.mypetproject.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Data
public class RegistrationForm {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String dateOfBirth;
    private String phone;
    private City city;
    private List<Advert> advertList;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(name, surname, email, passwordEncoder.encode(password), dateOfBirth, phone, city, advertList);
    }
}
