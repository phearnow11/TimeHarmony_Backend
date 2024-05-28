package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Watch;

public interface WatchRepository extends JpaRepository<Watch, String> {
    @Query("select w from Watch w where w.gender = ?1")
    List <Watch> findWatchesByGender(String gender);

    @Query("select w from Watch w where w.series = ?1")
    List <Watch> findWatchesBySeries(String series);

    @Query("select w from Watch w where w.brand = ?1")
    List <Watch> findWatchesByBrand(String brand);

    @Query("select w from Watch w where w.style_type = ?1")
    List <Watch> findWatchesByStyle(String style);
}
