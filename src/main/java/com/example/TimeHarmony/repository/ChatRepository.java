package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.Chat;

public interface ChatRepository extends JpaRepository<Chat, String> {
    @Query("select c from Chat c where cur_member = :mid")
    List<Chat> getMyChat(@Param("mid") UUID mid);

    @Modifying
    @Transactional
    @Query(value = "delete [dbo].[Chat] where cur_member = :cur_mid and with_member = :with_mid", nativeQuery = true)
    void deleteChat(@Param("cur_mid") UUID cur_mid, @Param("with_mid") UUID with_mid);
}
