package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.enumf.OrderState;

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
    String getOrderIdInWatchInCart(@Param("wid") String wid);

    @Query(value = "select [dbo].[Watch].state from [dbo].[Watch] join [dbo].[Watches_In_Cart] on [dbo].[Watch].watch_id = [dbo].[Watches_In_Cart].watch_id where order_id = :oid", nativeQuery = true)
    List<Integer> getOrderWatchStates(@Param("oid") String oid);

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Watches_In_Cart] set order_id = null where order_id = :oid", nativeQuery = true)
    void deleteOrder(@Param("oid") String oid);

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Orders] set state = :state where order_id = :oid", nativeQuery = true)
    void updateOrderState(@Param("state") int state, @Param("oid") String oid);

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Watches_In_Cart] set state = 0 where order_id = :oid", nativeQuery = true)
    void deleteWatchInCartWhenConfirm(@Param("oid") String oid);

    @Query(value = "select o.state from Orders o where o.order_id = :oid")
    OrderState getState(@Param("oid") String oid);

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Orders] set shipping_date = :date where order_id = :oid", nativeQuery = true)
    void updateShipDate(@Param("date") Timestamp date, @Param("oid") String oid);

    @Query(value = "select create_time from [dbo].[Orders] where order_id = :oid", nativeQuery = true)
    String getCreationDate(@Param("oid") String oid);

    @Query(value = "select * from [dbo].[Orders] where state = :state ", nativeQuery = true)
    List<Orders> getOrderByState(@Param("state") int state);
}
