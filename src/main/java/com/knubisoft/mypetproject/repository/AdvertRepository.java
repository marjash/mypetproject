package com.knubisoft.mypetproject.repository;

import com.knubisoft.mypetproject.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {

}
