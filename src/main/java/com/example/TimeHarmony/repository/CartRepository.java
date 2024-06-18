package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Cart;

import jakarta.transaction.Transactional;

public interface CartRepository extends JpaRepository<Cart, String> {

    @Query(value = "select * from Cart c where c.member_id = :m_id", nativeQuery = true)
    List<Cart> getCartFromMember(@Param("m_id") UUID m_id);

    @Modifying
    @Transactional
    @Query(value = "update Cart set checked = :check where cart_id = :id", nativeQuery = true)
    void updateCheck(@Param("check") int check, @Param("id") String id);

}
