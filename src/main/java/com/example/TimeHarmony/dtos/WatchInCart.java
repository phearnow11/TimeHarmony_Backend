package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;

public interface WatchInCart {
    String getWatch_id();

    String getCart_id();

    String getOrder_id();

    Byte getChecked();

    Timestamp getAdd_date();

    Byte getState();

    Float getPrice();

}
