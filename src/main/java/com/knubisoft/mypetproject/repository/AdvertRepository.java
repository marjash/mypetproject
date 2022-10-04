package com.knubisoft.mypetproject.repository;

import com.knubisoft.mypetproject.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    List<Advert> findAllByUserId(long userId);

    @Query(value = "select * from advert where name like %:keyword%", nativeQuery = true)
    List<Advert> findByAdvertName(@Param("keyword") String keyword);

    @Query(value = "select * from advert inner join category on advert.category_id = category.id where category.name like %:categoryName%",
            nativeQuery = true)
    List<Advert> findByCategoryName(@Param("categoryName") String categoryName);

    @Query(value = "select * from advert inner join city on advert.city_id = city.id where city.name like %:cityName%",
            nativeQuery = true)
    List<Advert> findByCityId(@Param("cityName") String cityName);

    @Transactional
    @Modifying
    @Query(value = "update advert set image_path = :path where id = :id", nativeQuery = true)
    void updateImagePath(@Param("path") String path, @Param("id") long id);
}
