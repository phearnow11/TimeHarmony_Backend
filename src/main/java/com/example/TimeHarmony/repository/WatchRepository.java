package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Watch;

public interface WatchRepository extends JpaRepository<Watch, String> {
    @org.springframework.data.jpa.repository.Query("select w from Watch w where w.gender = ?1")
    Watch findWatchesByGender(String gender);
}
