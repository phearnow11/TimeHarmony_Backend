package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Report;

public interface IReportService {
    Report addReport(Report r);

    List<Report> getMyReports(String mid);

    List<Report> getReportsAuthor(String rid);

    List<Report> getAllReports();

    Report createUnapproveReport(Map<String, String> data, String wid); 
}
