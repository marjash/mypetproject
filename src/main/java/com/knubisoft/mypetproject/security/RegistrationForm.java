package com.knubisoft.mypetproject.security;

import com.knubisoft.mypetproject.model.Advert;
import com.knubisoft.mypetproject.model.City;
import com.knubisoft.mypetproject.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class RegistrationForm {

    @NotNull(message = "Ім'я не можe бути пустим")
    @NotEmpty(message = "Ім'я не можe бути пустим")
    private String name;
    @NotNull(message = "Прізвище не можe бути пустим")
    @NotEmpty(message = "Прізвище не можe бути пустим")
    private String surname;

    @NotNull(message = "Імейл не можe бути пустим")
    @NotEmpty(message = "Імейл не можу бути пустим")
    @Email
    private String email;

    @NotNull(message = "Пароль не можу бути пустим")
    @NotEmpty(message = "Пароль не можe бути пустим")
    private String password;
    private String dateOfBirth;
    private String phone;
    private City city;
    private List<Advert> advertList;
    private LocalDate dateOfRegistration;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(name, surname, email, passwordEncoder.encode(password), dateOfBirth, phone, city, advertList, dateOfRegistration);
    }
}
