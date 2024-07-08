package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Vouchers {
    @Id
    private String voucher_id;
    private String name;
    private String description;
    private int value;
    private float value_percentage;
    private int limit;
    private int condition;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp created_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp expired_date;

    private int quantity;

    public Vouchers() {
    }
}
