package com.knubisoft.mypetproject.model;

import javax.persistence.*;

import lombok.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        File[] files = getFiles();
        if (files == null) return null;
        return imagePath + "/" + files[0].getName();
    }

    @Transient
    public List<String> getAllImages(){
        File[] files = getFiles();
        List<String> list = new ArrayList<>();
        for (File f : files) {
            list.add(f.getName());
        }
        return list;
    }

    @Transient
    private File[] getFiles() {
        if (imagePath == null)
            return null;
        String path = imagePath.replace("\\", "/");
        path = path.substring(1);
        File file = new File(path);
        return file.listFiles();
    }

}

