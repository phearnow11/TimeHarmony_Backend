package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.TimeHarmony.enumf.Reports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@Entity
@ToString
public class Report {
    @Id
    private String report_id;
    private UUID from_member;
    private UUID to_member;
    private String watch_id;
    private String order_id;
    private Reports type;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_date;

    public Report() {
    }

}
