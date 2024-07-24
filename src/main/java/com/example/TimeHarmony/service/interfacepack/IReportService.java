package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Report;

public interface IReportService {
    Report addReport(Report r);

    List<Report> getMyReports(String mid);

    List<Report> getReportsAuthor(String mid);

    List<Report> getAllReports();
}
