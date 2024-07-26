package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.enumf.Reports;
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
    public Report createUnapproveReport(Map<String, String> data, String wid) {
       
        Report newreport = new Report(); 
        String reportId = 'R' + STRING_SERVICE.autoGenerateString(11); 
        
        newreport.setReport_id(reportId);
        newreport.setWatch_id(wid);
        newreport.setOrder_id(data.get("order_id"));
        newreport.setType(Reports.APPRAISER_REPORT); 
        newreport.setTo(UUID.fromString(data.get("to")));
        newreport.setFrom(UUID.fromString(data.get("from"))); 
        newreport.setCreated_date(Timestamp.valueOf(LocalDateTime.now()));
        
        return REPORT_REPOSITORY.save(newreport); 
    }



    
}
