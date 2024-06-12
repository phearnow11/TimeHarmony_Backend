package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, String> {

}
