package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;

import com.example.TimeHarmony.enumf.WatchCartState;

public interface WatchInCart {
    String getWatch_id();

    String getCart_id();

    String getOrder_id();

    boolean getChecked();

    Timestamp getAdd_date();

    WatchCartState getState();

}
