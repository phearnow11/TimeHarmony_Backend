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

    @Query("select w from Watch w where w.dial_color = ?1")
    List<Watch> findWatchesByDialColor(String dial_color);

    @Query("select w from Watch w where w.case_shape = ?1")
    List<Watch> findWatchesByCaseShape(String caseshape);

    @Query("select w from Watch w where w.band_type = ?1")
    List<Watch> findWatchesByBandType(String bandtype);

    @Query("select w from Watch w where w.movement = ?1")
    List<Watch> findWatchesByMovement(String movement);
    
    @Query(value = "select top (30) * from Watch order by watch_create_date desc", nativeQuery = true)
    List<Watch> find30watchesByDESCDate();

    @Query(value = "select top (60) * from Watch order by watch_create_date desc", nativeQuery = true)
    List<Watch> get1pageOfWatchByDESCDate();

    @Query(value = "select * from Watch order by watch_create_date desc OFFSET :start rows fetch next 18 rows only", nativeQuery = true)
    List<Watch> findNext18WatchesbyDESCDate(@Param("start") int start);

    @Query(value = "select * from Watch order by watch_create_date desc OFFSET :start rows fetch next 60 rows only", nativeQuery = true)
    List<Watch> findNextPageDESCDate(@Param("start") int start);

    @Query(value = "select * from Watch where feature like %:start% ", nativeQuery= true)
    List<Watch> findWatchesByFeatures(@Param("start") String feature);

    @Query(value = "select * from Watch where price BETWEEN :start AND :end", nativeQuery=true)
    List<Watch> findWatchByRangePrice(@Param("start") float start, @Param ("end") float end);

}
