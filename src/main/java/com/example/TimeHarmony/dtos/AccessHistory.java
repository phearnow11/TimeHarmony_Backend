package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public interface AccessHistory {
    UUID getMember_id();

    String getUrl();

    Timestamp getAccess_time();
}
