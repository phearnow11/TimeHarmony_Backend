package com.example.TimeHarmony.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Addresses {
    @Id
    private String address_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "addresses", "handler", "hibernateLazyInitializer",
            "watches" }, allowSetters = true)
    @JoinColumn(name = "member_id")
    private Members member;

    private String name;
    private String phone;
    private String address_detail;
    private boolean is_default;

    public Addresses() {
    }

}
