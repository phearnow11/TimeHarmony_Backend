package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Staff;

import jakarta.transaction.Transactional;

public interface StaffRepository extends JpaRepository<Staff, UUID> {

    @Modifying
    @Transactional
    @Query(value = "insert Staff(member_id) values (:id)", nativeQuery = true)
    void toStaff(@Param("id") UUID id);
}
