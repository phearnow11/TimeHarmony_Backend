package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.dtos.WatchImages;
import com.example.TimeHarmony.entity.Watch;

import jakarta.transaction.Transactional;

public interface WatchRepository extends JpaRepository<Watch, String> {
        @Query("select w from Watch w where w.gender = ?1 and w.state = 2")
        List<Watch> findWatchesByGender(String gender);

        @Query("select w from Watch w where w.series = ?1 and w.state = 2")
        List<Watch> findWatchesBySeries(String series);

        @Query("select w from Watch w where w.brand = ?1 and w.state = 2")
        List<Watch> findWatchesByBrand(String brand);

        @Query("select w from Watch w where w.style_type = ?1 and w.state = 2")
        List<Watch> findWatchesByStyle(String style);

        @Query("select w from Watch w where w.dial_color = ?1 and w.state = 2")
        List<Watch> findWatchesByDialColor(String dial_color);

        @Query("select w from Watch w where w.case_shape = ?1 and w.state = 2")
        List<Watch> findWatchesByCaseShape(String caseshape);

        @Query("select w from Watch w where w.band_type = ?1 and w.state = 2")
        List<Watch> findWatchesByBandType(String bandtype);

        @Query("select w from Watch w where w.movement = ?1 and w.state = 2")
        List<Watch> findWatchesByMovement(String movement);

        @Query(value = "select * from [dbo].[Watch] join [dbo].[Favorites] on [dbo].[Watch].watch_id = [dbo].[Favorites].watch_id where [dbo].[Favorites].member_id = :mid and state = 2 ", nativeQuery = true)
        List<Watch> findWatchesByFavorites(@Param("mid") UUID mid);

        @Query(value = "select top (30) * from Watch where state = 2 order by watch_create_date desc ", nativeQuery = true)
        List<Watch> find30watchesByDESCDate();

        @Query(value = "select top (60) * from Watch where state = 2 order by watch_create_date desc ", nativeQuery = true)
        List<Watch> get1pageOfWatchByDESCDate();

        @Query(value = "select * from Watch where state = 2 order by watch_create_date desc OFFSET :start rows fetch next 18 rows only", nativeQuery = true)
        List<Watch> findNext18WatchesbyDESCDate(@Param("start") int start);

        @Query(value = "select * from Watch where state = 2 order by watch_create_date desc OFFSET :start rows fetch next 60 rows only", nativeQuery = true)
        List<Watch> findNextPageDESCDate(@Param("start") int start);

        @Query(value = "select * from Watch where feature like %:start% and state = 2", nativeQuery = true)
        List<Watch> findWatchesByFeatures(@Param("start") String feature);

        @Query(value = "select * from Watch where price BETWEEN :start AND :end and state = 2", nativeQuery = true)
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
        @Query(value = "update [dbo].[Watch] set [dbo].[Watch].state = 4 from [dbo].[Watch] join [dbo].[Watches_In_Cart] on [dbo].[Watches_In_Cart].watch_id = [dbo].[Watch].watch_id where order_id = :oid ", nativeQuery = true)
        void confirmOrder(@Param("oid") String oid);

        @Modifying
        @Transactional
        @Query(value = "update [dbo].[Watch] set [dbo].[Watch].state = 2 from [dbo].[Watch] join [dbo].[Watches_In_Cart] on [dbo].[Watches_In_Cart].watch_id = [dbo].[Watch].watch_id where order_id = :oid ", nativeQuery = true)
        void cancelOrder(@Param("oid") String oid);

        @Query("select w from Watch w where  w.state = :state")
        List<Watch> getWatchesByState(@Param("state") int state);

        @Modifying
        @Transactional
        @Query(value = "delete Watch_Images where watch_id = :id and image_url = :url", nativeQuery = true)
        void deleteImage(@Param("id") String id, @Param("url") String url);

        List<Watch> findAllByState(byte state);

        @Query(value = "select * from Watch where freetext(Watch.*, :key) AND state = 2", nativeQuery = true)
        List<Watch> findByKeyWord(@Param("key") String key);

        @Query(value = "select * from Watch where watch_name like %:key% or watch_description like %:key% and state = 2 ", nativeQuery = true)
        List<Watch> findByKeyIfFullTextNull(@Param("key") String key);

        @Query(value = "select COUNT(watch_id) as watch_num from Watch", nativeQuery = true)
        Integer getWatchNum();

        @Modifying
        @Transactional
        @Query(value = "update Watch set watch_approval_date = :date, state = :state where watch_id = :id", nativeQuery = true)
        void approveWatch(@Param("date") Timestamp date, @Param("id") String wid, @Param("state") byte steate);

        @Query(value = "select w from Watch w where (:gender is null or w.gender like :gender) and (:series is null or w.series like :series) and (:brand is null or w.brand like :brand) and (:style is null or w.style_type like :style) and (:feature is null or w.feature like :feature) and w.price > :lprice and w.price < :hprice and w.state = 1 and w.watch_name like %:key%")
        List<Watch> getWatchesByFilter(@Param("key") String key, @Param("gender") String gender,
                        @Param("series") String series,
                        @Param("brand") String brand,
                        @Param("style") String style, @Param("feature") String feature, @Param("lprice") float lowprice,
                        @Param("hprice") float hprice, Pageable pageable);

        @Query(value = "select * from Watch where member_id = :mid", nativeQuery = true)
        List<Watch> getWatchesBySeller(@Param("mid") UUID mid, Limit limit);

        @Query(value = "select * from Watch where member_id = :mid and state = 3", nativeQuery = true)
        List<Watch> getWaitingWatches(@Param("mid") UUID mid, Limit limit);

        @Query(value = "select * from [dbo].[Watch_images] where watch_id = :wid", nativeQuery = true)
        List<WatchImages> getWatchImages(@Param("wid") String wid);

        @Query(value = "select COUNT(watch_id) as watch_num from Watch w where (:gender is null or w.gender like :gender) and (:series is null or w.series like :series) and (:brand is null or w.brand like :brand) and (:style is null or w.style_type like :style) and (:feature is null or w.feature like :feature) and w.price > :lprice and w.price < :hprice and w.state = 1")
        Integer getWatchNumWithConditions(@Param("gender") String gender, @Param("series") String series,
                        @Param("brand") String brand,
                        @Param("style") String style, @Param("feature") String feature, @Param("lprice") float lowprice,
                        @Param("hprice") float hprice);
}
