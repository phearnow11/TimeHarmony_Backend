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
public class Chat {
    @Id
    private String chat_id;
    private UUID cur_member;
    private UUID with_member;

    @Temporal(TemporalType.TIMESTAMP)
    Timestamp created_date;
}
