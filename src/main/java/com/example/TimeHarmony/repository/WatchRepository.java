package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Watch;

public interface WatchRepository extends JpaRepository<Watch, String> {
    @Query("select w from Watch w where w.gender = ?1")
    List<Watch> findWatchesByGender(String gender);

    @Query("select w from Watch w where w.series = ?1")
    List<Watch> findWatchesBySeries(String series);

    @Query("select w from Watch w where w.brand = ?1")
    List<Watch> findWatchesByBrand(String brand);

    @Query("select w from Watch w where w.style_type = ?1")
    List<Watch> findWatchesByStyle(String style);

    @Query("select w from Watch w where w.feature = ?1")
    List<Watch> findWatchesByFeatures(String feature);

    @Query(value = "select top (30) * from Watch order by watch_create_date desc", nativeQuery = true)
    List<Watch> find30watchesByDESCDate();

    @Query(value = "select * from Watch order by watch_create_date desc OFFSET :start rows fetch next 18 rows only", nativeQuery = true)
    List<Watch> findNext18WatchesbyDESCDate(@Param("start") int start);
}
