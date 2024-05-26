package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
}
