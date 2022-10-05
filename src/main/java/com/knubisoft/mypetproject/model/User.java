package com.knubisoft.mypetproject.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    @NotNull
//    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;

    @Column(unique = true)
    private String email;
    private String password;

    @Column(name = "date_of_bitrh")
    private String dateOfBirth;

    private String phone;

    @ManyToOne
    private City city;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Advert> advertList = new ArrayList<>();

    @Column(name = "date_of_registration")
    private LocalDate dateOfRegistration;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(String name, String surname, String email, String password, String dateOfBirth, String phone, City city, List<Advert> advertList, LocalDate dateOfRegistration) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.city = city;
        this.advertList = advertList;
        this.dateOfRegistration = dateOfRegistration;
    }
}
