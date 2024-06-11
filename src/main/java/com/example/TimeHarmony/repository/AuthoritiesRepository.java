package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Authorities;

import jakarta.transaction.Transactional;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

    @Modifying
    @Transactional
    @Query(value = "update Authorities set authority = :role where username = :username", nativeQuery = true)
    void updateRole(@Param("role") String role, @Param("username") String username);

}
