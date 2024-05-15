package com.example.TimeHarmony.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Members;

public interface MemberRepository extends JpaRepository<Members, Long> {
    @Query("select u from Members u where u.member_id = ?1")
    Optional<Members> findMemberByID(String member_id);

}
