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
  @Query(value = "update [dbo].[Payment] set order_id = null where order_id = :oid ", nativeQuery= true)
  void deleteOrderIdInPayment(@Param("oid") String oid); 

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

  @Query(value = "select [dbo].[Orders].order_id from [dbo].[Orders] join [dbo].[Shipping_Order] on [dbo].[Orders].order_id = [dbo].[Shipping_Order].order_id where shipper = :shipper", nativeQuery = true)
  List<String> getShippingOrderFromShipper(@Param("shipper") UUID shipper);

  @Query(value = "select [dbo].[Orders].order_id from [dbo].[Orders] join [dbo].[Shipping_Order] on [dbo].[Orders].order_id = [dbo].[Shipping_Order].order_id", nativeQuery = true)
  List<String> getAllShippingOrder();

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Orders] set state = 2 , shipping_date = getdate() where order_id = :oid", nativeQuery = true)
  void shipOrder(@Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Orders] set state = 6 , shipped_date = getdate() where order_id = :oid", nativeQuery = true)
  void shippedOrder(@Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Orders] set state = 3 , received_date = getdate() where order_id = :oid", nativeQuery = true)
  void successOrder(@Param("oid") String oid);

  @Query(value = "select [dbo].[Watches_In_Cart].watch_id from [dbo].[Orders] join [dbo].[Watches_In_Cart] on [dbo].[Orders].order_id = [dbo].[Watches_In_Cart].order_id where watch_id =: wid and MONTH(received_date) = :month", nativeQuery = true)
  String getReceivedWatch(@Param("month") int month);

  @Query(value = "select payment_method from [dbo].[Payment] where order_id = :oid", nativeQuery = true)
  String getPaymentMethod(@Param("oid") String oid);

  @Query(value = "select sum ([dbo].[Orders].[total_price]/51) from [dbo].[Orders] where state <> 4 and state <> 5", nativeQuery = true)
  String  getWebProfit();

  @Query(value = "select order_id from [dbo].[Watches_In_Cart] join (select watch_id from [dbo].[Watch] join [dbo].[Members] on [dbo].[Watch].member_id = [dbo].[Members].member_id where [dbo].[Members].username = :username) as M on [dbo].[Watches_In_Cart].watch_id = M.watch_id", nativeQuery = true)
  List<String> getOrderFromSeller(@Param("username") String username);

  @Query(value = "select state from Orders where order_id in :oids", nativeQuery= true)
  List<OrderState> getStatesFromOrder(@Param("oids") List<String> oids);

  @Query(value = "select o from Orders o where o.state = ?1")
  List<Orders> getOrdersByState(int state); 


  @Query(value= "SELECT COUNT(*) FROM [dbo].[Orders] WHERE CAST(create_time AS DATE) = :date and state = 3 ", nativeQuery=true)
  String  NumOfOrderSuccessByDate(@Param("date") String date); 

  @Query(value="Select count(*) from [dbo].[Orders] where cast(create_time as date) = :date ", nativeQuery=true)
  String NumOfOrderByDate(@Param("date") String date); 

  @Query(value = "SELECT SUM(total_price) FROM [dbo].[Orders] WHERE CAST(create_time AS DATE) = :date and state = 3 ", nativeQuery=true)
  String totalPriceOrderSuccessByDate(@Param("date") String date); 

  @Query(value = "SELECT SUM(total_price) FROM [dbo].[Orders] WHERE CAST(create_time AS DATE) = :date ", nativeQuery=true)
  String totalPriceOrderByDate(@Param("date") String date); 

  @Query(value = "select sum ([dbo].[Orders].[total_price]/51) from [dbo].[Orders] WHERE (state <> 4 and state <> 5) and CAST(create_time AS DATE) between :from and :to ", nativeQuery = true)
  String getWebProfitByDate(@Param("from") String from, @Param("to") String to);

  @Query(value = "SELECT SUM([total_price]/51)  FROM [dbo].[Orders] WHERE (state <> 4 and state <> 5 ) and CONVERT(VARCHAR(7), create_time, 120) BETWEEN :fromM AND :toM", nativeQuery= true)
  String getWebProfitByMonth(@Param("fromM") String fromM, @Param("toM") String toM);
}
