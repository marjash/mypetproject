package com.knubisoft.mypetproject;

import java.util.List;
import java.util.Optional;

public interface AdvertService {
    Advert save(Advert ad);
    Optional<Advert> readById(long id);
//    Advert update(Advert authorName);
    void delete(long id);
    List<Advert> getAll();
}
