package com.example.TimeHarmony.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface MemberRepository extends JpaRepository<Members, UUID> {
    @Query("select m from Members m where m.user_log_info = ?1")
    Optional<Members> getMemberbyUserLogInfo(Users user);
}
