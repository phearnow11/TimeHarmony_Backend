package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Vouchers;

import jakarta.transaction.Transactional;

public interface VoucherRepository extends JpaRepository<Vouchers, String> {

    @Modifying
    @Transactional
    @Query(value = "update [dbo].[Vouchers] set quantity = (select quantity from [dbo].[Vouchers] where voucher_id = :vid) - :quantity where voucher_id = :vid", nativeQuery = true)
    void removeNumberOfVoucher(@Param("quantity") int quantity, @Param("vid") String vid);

    @Query(value = "select * from [dbo].[Vouchers] where expired_date < getdate()", nativeQuery = true)
    List<Vouchers> getVouchersNotExpired();
}
