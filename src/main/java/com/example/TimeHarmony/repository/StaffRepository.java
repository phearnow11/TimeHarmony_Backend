package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Staff;
import com.example.TimeHarmony.enumf.StaffRole;

import jakarta.transaction.Transactional;

public interface StaffRepository extends JpaRepository<Staff, UUID> {

    @Modifying
    @Transactional
    @Query(value = "insert Staff(member_id) values (:id)", nativeQuery = true)
    void toStaff(@Param("id") UUID id);

    @Query(value = "select st.staff_role from Staff st where member_id = :id")
    StaffRole getRole(@Param("id") UUID id);
}
