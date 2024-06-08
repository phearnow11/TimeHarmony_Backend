package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {
    
}
