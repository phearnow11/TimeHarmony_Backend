package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.dtos.SellerRate;
import com.example.TimeHarmony.entity.Sellers;

public interface SellerRepository extends JpaRepository<Sellers, UUID> {

    @Modifying
    @Transactional
    @Query(value = "insert Sellers(member_id) values (:id)", nativeQuery = true)
    void toSeller(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = "insert Seller_Rate(member_id, rate, rater) values (:id, :rate, :rater_id)", nativeQuery = true)
    void updateRate(@Param("id") UUID id, @Param("rate") float rate, @Param("rater_id") UUID rater);

    @Query(value = "select * from [dbo].[Seller_Rate] where rater = :rater", nativeQuery = true)
    List<SellerRate> checkExistRater(@Param("rater") UUID rater);

    @Query(value = "select AVG(rate) from [dbo].[Seller_Rate] where member_id = :sid", nativeQuery = true)
    float getRate(@Param("sid") UUID sid);
}
