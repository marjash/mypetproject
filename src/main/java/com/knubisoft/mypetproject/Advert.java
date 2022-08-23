package com.knubisoft.mypetproject;

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

//    @Column(name = "name")
    private String name;

//    @Column(name = "category")
    private String category;

//    @Column(name = "price")
    private int price;

//    @Column(name = "city")
    private String city;

//    @Column(name = "phone")
    private String phone;

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
