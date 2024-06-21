package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;

public interface WatchInCart {
    String getWatch_id();

    String getCart_id();

    boolean getChecked();

    Timestamp getAdd_date();
}
