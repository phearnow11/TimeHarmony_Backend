package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Orders;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Orders, String> {

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Watches_In_Cart] set order_id = :oid where cart_id = :cid and watch_id = :wid", nativeQuery = true)
    void updateOrderCart(@Param("oid") String oid, @Param("cid") String cid, @Param("wid") String wid);

    @Query(value = "select * from [dbo].[Orders] where member_id = :mid ", nativeQuery = true)
    List<Orders> getOrdersByMemberId(@Param("mid") UUID mid);

    @Query(value = "select [dbo].[Watches_In_Cart].watch_id from [dbo].[Watches_In_Cart] join [dbo].[Orders] on [dbo].[Orders].order_id = [dbo].[Watches_In_Cart].order_id where [dbo].[Watches_In_Cart].order_id = :oid", nativeQuery = true)
    List<String> getWatchesInOrder(@Param("oid") String oid);

    @Query(value = "select [dbo].[Watches_In_Cart].order_id from [dbo].[Watches_In_Cart] where watch_id = :wid ", nativeQuery = true)
    String getOrderIdInWatchInCart(@Param("wid")String wid); 
}
