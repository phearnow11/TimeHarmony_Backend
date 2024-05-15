package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Members;

public interface MemberRepository extends JpaRepository<Members, Long> {

}
