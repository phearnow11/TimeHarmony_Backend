package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Addresses;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Addresses, String> {
    @Modifying
    @Transactional
    @Query(value = "insert Addresses([address_id], [member_id], [name], [phone], [address_detail], [is_default]) values (:id , :m_id , :name , :phone , :detail )", nativeQuery = true)
    void insertAddress(@Param("id") String id, @Param("m_id") UUID m_id, @Param("name") String name,
            @Param("detail") String detail);
}
