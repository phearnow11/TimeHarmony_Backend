package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Orders;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Modifying
    @Transactional
    @Query(value = "update Watches_In_Cart set order_id = :oid where cart_id = :cid and watch_id = :wid", nativeQuery = true)
    void updateOrderCart(@Param("oid") String oid, @Param("cid") String cid, @Param("wid") String wid);

}
