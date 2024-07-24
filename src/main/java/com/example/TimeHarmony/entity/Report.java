package com.example.TimeHarmony.entity;

import java.security.Timestamp;
import java.util.UUID;

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
    private UUID from;
    private UUID to;
    private String watch_id;
    private String order_id;
    private Reports type;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_date;

    public Report() {
    }

}
