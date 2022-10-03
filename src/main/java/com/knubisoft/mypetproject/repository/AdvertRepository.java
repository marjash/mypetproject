package com.knubisoft.mypetproject.repository;

import com.knubisoft.mypetproject.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    List<Advert> findAllByUserId(long userId);

    @Query(value = "select * from advert where name like %:keyword%", nativeQuery = true)
    List<Advert> findByAdvertName(@Param("keyword") String keyword);
}
