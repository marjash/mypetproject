package com.knubisoft.mypetproject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private String surname;
    private String email;
    private String date;
    private String city;
}
