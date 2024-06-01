package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccessHistory {
    private UUID member_id;
    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp access_time;
}
