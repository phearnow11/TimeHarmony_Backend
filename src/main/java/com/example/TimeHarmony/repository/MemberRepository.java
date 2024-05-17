package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;

public interface MemberRepository extends JpaRepository<Members, String> {

}
