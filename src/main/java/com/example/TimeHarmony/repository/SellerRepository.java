package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.Sellers;

public interface SellerRepository extends JpaRepository<Sellers, UUID> {

    @Modifying
    @Transactional
    @Query(value = "insert Sellers(member_id) values (:id)", nativeQuery = true)
    void toSeller(@Param("id") UUID id);

}
