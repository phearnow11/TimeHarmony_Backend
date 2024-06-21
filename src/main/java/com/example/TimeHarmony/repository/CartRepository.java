package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.dtos.WatchInCart;
import com.example.TimeHarmony.entity.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, String> {

    @Modifying
    @Transactional
    @Query(value = "insert into Watches_In_Cart(watch_id, cart_id, checked, add_date) values (:wid, :cid, :checked, :add_date)", nativeQuery = true)
    void addToCart(@Param("wid") String wid, @Param("cid") String cid, @Param("checked") int checked,
            @Param("add_date") Timestamp time);

    @Query(value = "select * from Watches_In_Cart where cart_id = :id", nativeQuery = true)
    List<WatchInCart> getWatchesInCart(@Param("id") String cid);

    @Modifying
    @Transactional
    @Query(value = "update Watches_In_Cart set checked = :checked where cart_id = :cid and watch_id = :wid", nativeQuery = true)
    void updateChecked(@Param("checked") int check, @Param("cid") String cid, @Param("wid") String wid);
}
