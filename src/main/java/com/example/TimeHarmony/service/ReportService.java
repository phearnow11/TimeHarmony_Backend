package com.example.TimeHarmony.service;

import java.sql.Timestamp;
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
    public UUID getReportsAuthorId(String rid) {
        Report r = REPORT_REPOSITORY.findReportAuthor(rid).get(); 
        return r.getFrom_member(); 
    }

    @Override
    public List<Report> getAllReports() {
        return REPORT_REPOSITORY.findAll();
    }

    @Override
    public Report createReport(Map<String, String> data) {
        Report newreport = new Report();
        String reportId = 'R' + STRING_SERVICE.autoGenerateString(11);
        newreport.setReport_id(reportId);
        newreport.setWatch_id(data.get("watch_id"));
        newreport.setOrder_id(data.get("order_id"));
        newreport.setTo_member((data.get("to")) != null ? UUID.fromString(data.get("to")) : null);
        newreport.setFrom_member((data.get("from")) != null ? UUID.fromString(data.get("from")) : null);
        newreport.setContent(data.get("content"));
        newreport.setCreated_date(Timestamp.valueOf(LocalDateTime.now()));
        int type = Integer.parseInt(data.get("type"));
        switch (type) {
            case 0:
                newreport.setType(Reports.ORDER_REPORT);
                return REPORT_REPOSITORY.save(newreport);

            case 1:
                newreport.setType(Reports.APPRAISER_REPORT);
                System.out.println(newreport);
                return REPORT_REPOSITORY.save(newreport);
            case 2:
                newreport.setType(Reports.MEMBER_REPORT);
                return REPORT_REPOSITORY.save(newreport);
        }
        System.out.println("Just select report type from 0 to 2 !");
        return null;
    }

}
