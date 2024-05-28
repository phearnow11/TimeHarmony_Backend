package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Members;

public interface MemberRepository extends JpaRepository<Members, UUID> {
    @Query("select m from Members where m.username = ?1")
    Members getMemberbyUsername(String username);
}
