package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.repository.ReportRepository;
import com.example.TimeHarmony.service.interfacepack.IReportService;

@Service
public class ReportService implements IReportService {

    @Autowired 
    private ReportRepository REPORT_REPOSITORY; 

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Override
    public Report addReport(Report r) {
        return REPORT_REPOSITORY.save(r); 
    }

    @Override
    public List<Report> getMyReports(String mid) {
        
        return REPORT_REPOSITORY.findMyreportList(mid); 
    }

    @Override
    public List<Report> getReportsAuthor(String rid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReportsAuthor'");
    }

    @Override
    public List<Report> getAllReports() {
       return REPORT_REPOSITORY.findAll(); 
    }

    @Override
    public Report unApprovReport(String wid) {
        

        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unApprovReport'");
    }

}
