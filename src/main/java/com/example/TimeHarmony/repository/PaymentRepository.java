package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Payment;

import jakarta.transaction.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Payment] set order_id = :oid where transaction_no = :tno", nativeQuery = true)
    void updateOrderid(@Param("oid") String oid, @Param("tno") String tno);

    @Modifying
    @Transactional
    @Query(value= "select * from [dbo].[Payment] where order_id is null", nativeQuery = true)
    List<Payment> getAllFailOrder(); 

    // Thinh
    @Transactional
    @Query(value = "SELECT transaction_no FROM [dbo].[Payment] WHERE order_id = :oid", nativeQuery = true)
    String getTransactionNoByOrderId(@Param("oid") String oid);
}
