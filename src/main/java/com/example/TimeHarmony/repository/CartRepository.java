package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, String> {

}
