package com.knubisoft.mypetproject.model;

import javax.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private Category category;

    private int price;

    @ManyToOne
    private City city;

    private String phone;

    @ManyToOne
    private User user;

    private String image;

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
