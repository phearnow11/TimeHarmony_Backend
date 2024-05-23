package com.example.TimeHarmony.entity;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Report {
    @Id
    private String report_id;
    private String member_id;
    private String watch_id;
    private String report_content;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp report_date;

    private byte report_type;

    public Report(String member_id, String report_content, Timestamp report_date, String report_id, byte report_type,
            String watch_id) {
        this.member_id = member_id;
        this.report_content = report_content;
        this.report_date = report_date;
        this.report_id = report_id;
        this.report_type = report_type;
        this.watch_id = watch_id;
    }

    public Report() {
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(String watch_id) {
        this.watch_id = watch_id;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public Timestamp getReport_date() {
        return report_date;
    }

    public void setReport_date(Timestamp report_date) {
        this.report_date = report_date;
    }

    public byte getReport_type() {
        return report_type;
    }

    public void setReport_type(byte report_type) {
        this.report_type = report_type;
    }

}
