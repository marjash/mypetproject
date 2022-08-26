package com.knubisoft.mypetproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<User> userList = new ArrayList<>();

    @OneToMany(
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Advert> advertList = new ArrayList<>();
}
