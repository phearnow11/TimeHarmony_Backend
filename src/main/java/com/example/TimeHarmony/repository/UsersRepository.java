package com.example.TimeHarmony.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
    @Query(value = "select * from Users where username = ?1", nativeQuery = true)
    Optional<Users> getUserbyUsername(String username);
}
