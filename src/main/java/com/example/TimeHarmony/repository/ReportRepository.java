package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Report;

public interface ReportRepository extends JpaRepository<Report, String> {
    
    @Query("select r from Report r where from_member = ?1")
    List<Report> findMyreportList(String mid); 

    @Query("select r from Report r where report_id = ?1")
    Optional<Report> findReportAuthor (String rid); 

}
