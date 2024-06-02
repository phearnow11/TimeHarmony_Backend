package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, UUID> {

}
