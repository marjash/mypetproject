package com.knubisoft.mypetproject.model;

import javax.persistence.*;

import lombok.*;

import java.io.File;
import java.time.LocalDate;

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

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "date_of_creation")
    private LocalDate dateOfCreation;

    @Transient
    public String getFirstPhoto() {
        if (imagePath == null)
            return null;
        String path = imagePath.replace("\\", "/");
        path = path.substring(1);
        File file = new File(path);
        File[] files = file.listFiles();
        return imagePath + "/" + files[0].getName();
    }

}

