package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Watch;

import jakarta.transaction.Transactional;

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

    @Query(value = "select * from Watch where feature like %:start% ", nativeQuery = true)
    List<Watch> findWatchesByFeatures(@Param("start") String feature);

    @Query(value = "select * from Watch where price BETWEEN :start AND :end", nativeQuery = true)
    List<Watch> findWatchByRangePrice(@Param("start") float start, @Param("end") float end);

    @Modifying
    @Transactional
    @Query(value = "insert into Watch_images(watch_id, image_url) values (:id, :url)", nativeQuery = true)
    void saveWatch_Images(@Param("id") String id, @Param("url") String url);

    @Modifying
    @Transactional
    @Query(value = "update Watch set state = :state where watch_id = :id", nativeQuery = true)
    void updateWatchState(@Param("state") byte state, String id);

    @Modifying
    @Transactional
    @Query(value = "delete Watch_Images where watch_id = :id and image_url = :url", nativeQuery = true)
    void deleteImage(@Param("id") String id, @Param("url") String url);

    List<Watch> findAllByState(byte state);

    @Query(value = "select * from Watch where freetext(Watch.*, :key) AND state = 1", nativeQuery = true)
    List<Watch> findByKeyWord (@Param("key") String key);

    @Query(value = "select * from dbo.Watch where watch_name like '%:key%' or watch_description like '%:key%' and state = 0 ", nativeQuery= true)
    List<Watch> findByKeyIfFullTextNull (@Param("key") String key);
}
