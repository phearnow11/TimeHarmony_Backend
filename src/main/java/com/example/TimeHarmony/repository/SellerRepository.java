package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Sellers;

public interface SellerRepository extends JpaRepository<Sellers, UUID> {

}
