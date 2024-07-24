package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.service.interfacepack.IReportService;

@Service
public class ReportService implements IReportService {

    @Override
    public Report addReport(Report r) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReport'");
    }

    @Override
    public List<Report> getMyReports(String mid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMyReports'");
    }

    @Override
    public List<Report> getReportsAuthor(String mid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReportsAuthor'");
    }

    @Override
    public List<Report> getAllReports() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllReports'");
    }

}
