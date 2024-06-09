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
public class Cart {
    @Id
    private String cart_id;
    private String watch_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp add_date;

    public Cart() {
    }
}
