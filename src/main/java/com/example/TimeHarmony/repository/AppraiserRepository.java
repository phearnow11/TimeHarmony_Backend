package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Appraisers;

public interface AppraiserRepository extends JpaRepository<Appraisers, UUID> {

}
