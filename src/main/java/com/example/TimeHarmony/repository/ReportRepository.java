package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Report;

public interface ReportRepository extends JpaRepository<Report, String> {
    
    @Query("select r from Report r where from = ?1")
    List<Report> findMyreportList(String mid); 

    

}
