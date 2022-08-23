package com.knubisoft.mypetproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("advertService")
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertRepository advert;

    @Override
    public Advert save(Advert ad) {
        return advert.save(ad);
    }

    @Override
    public Optional<Advert> readById(long id) {
        return advert.findById(id);
    }

//    @Override
//    public Advert update(Advert name) {
//        advert.
//        return null;
//    }

    @Override
    public void delete(long id) {
        advert.deleteById(id);
    }

    @Override
    public List<Advert> getAll() {
        return advert.findAll();
    }
}
