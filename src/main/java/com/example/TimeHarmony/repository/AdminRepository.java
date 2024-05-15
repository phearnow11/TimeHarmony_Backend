package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Admins;

public interface AdminRepository extends JpaRepository<Admins, String> {

}
