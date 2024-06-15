package com.example.TimeHarmony.entity;

import java.security.Timestamp;

import com.example.TimeHarmony.enumf.Reports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Report {
    @Id
    private String report_id;
    private String member_id;
    private String watch_id;
    private String report_content;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp report_date;

    private Reports report_type;

    public Report() {
    }

}
