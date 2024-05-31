package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;
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

    @Query("select m from Members m where m.email = :email")
    List<Members> getMemberbyEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query("update Members m set m.last_login_date = :date where m.member_id = :id")
    void updateLastLoginDate(@Param("date") Timestamp date, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.last_logout_date = :date where m.member_id = :id")
    void updateLastLogoutDate(@Param("date") Timestamp date, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.is_active = :status where m.member_id = :id")
    void updateActiveStatus(@Param("status") int status, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.email_verification = :code where m.member_id = :id")
    void updateEmailVerificationCode(@Param("code") String code, @Param("id") UUID id);

    @Modifying
    @Transactional
    @Query("update Members m set m.email = :email where m.member_id = :id")
    void updateMemberEmail(@Param("email") String email, @Param("id") UUID id);
}
