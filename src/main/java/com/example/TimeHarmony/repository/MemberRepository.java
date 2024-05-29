package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface MemberRepository extends JpaRepository<Members, UUID> {
    @Query("select m from Members m where m.user_log_info = ?1")
    Optional<Members> getMemberbyUserLogInfo(Users user);

    @Modifying
    @Transactional
    @Query("update Members m set m.last_login_date = :date where m.member_id = :id")
    void setLastLoginDate(@Param("date") Timestamp date, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.last_logout_date = :date where m.member_id = :id")
    void setLastLogoutDate(@Param("date") Timestamp date, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.is_active = :status where m.member_id = :id")
    void setActiveStatus(@Param("status") int status, @Param("id") UUID id);
}
