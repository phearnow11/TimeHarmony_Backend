package com.example.TimeHarmony.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
  @Query(value = "select * from Users where username = ?1", nativeQuery = true)
  Optional<Users> getUserbyUsername(String username);

  @Modifying
  @Transactional
  @Query("update Users u set u.password = :password where u.username = :username")
  void updateUserPassword(@Param("password") String pwd, @Param("username") String username);

  @Modifying
  @Transactional
  @Query("update Users u set u.username = :new_username where u.username = :username")
  void updateUsername(@Param("new_username") String new_username, @Param("username") String username);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Users] set [enabled] = :state where [dbo].[Users].username = :username", nativeQuery = true)
  void updateUserState(@Param("state") int state, @Param("username") String username);

  @Query(value = "select [enabled] from [dbo].[Users] where [dbo].[Users].username = :username", nativeQuery = true)
  Integer getEnable(@Param("username") String username);

  @Modifying
  @Transactional
  @Query(value = "delete [dbo].[Users] from [dbo].[Users] join [dbo].[Members] on [dbo].[Users].username = [dbo].[Members].username where [dbo].[Members].member_id = :mid", nativeQuery = true)
  void deleteMember(@Param("mid") UUID mid);
}
