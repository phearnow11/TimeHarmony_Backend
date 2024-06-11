package com.example.TimeHarmony.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Voucher {
    @Id
    private String id;
    private String name;
    private String description;
    private int value;
    private float value_percentage;
    private int available;

    public Voucher() {
    }
}
