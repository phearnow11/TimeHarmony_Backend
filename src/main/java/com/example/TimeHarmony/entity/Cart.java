package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

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
public class Cart {
    @Id
    private String cart_id;
    private String watch_id;
    private UUID member_id;
    private String order_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp add_date;

    private int checked;

    public Cart() {
    }
}
