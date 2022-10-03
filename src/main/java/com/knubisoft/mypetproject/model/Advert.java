package com.knubisoft.mypetproject.model;

import javax.persistence.*;

import lombok.*;

import java.io.File;

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

    @Transient
    public String getPhotosImagePath() {
        if (imagePath == null)
            return null;
        String path = imagePath.replace("\\", "/");
        path = path.substring(1);
        File file = new File(path);
        File[] files = file.listFiles();
        return imagePath + "/" + files[0].getName();
    }
}

