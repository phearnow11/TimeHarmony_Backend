package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Users;

public interface MemberRepository extends JpaRepository<Users, String> {

}
