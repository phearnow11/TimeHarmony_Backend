package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.TimeHarmony.entity.Report;

public interface IReportService {
    Report addReport(Report r);

    List<Report> getMyReports(String mid);

    UUID getReportsAuthorId(String rid);

    List<Report> getAllReports();

    Report createReport( Map<String, String> data); 

}
