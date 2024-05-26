package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class Sellers extends Members {

    private String watch_id;

    public Sellers(UUID member_id, String username, String member_image, String first_name, String last_name,
            int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, String watch_id) {
        super(member_id, username, member_image, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification);
        this.watch_id = watch_id;
    }

    public String getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(String watch_id) {
        this.watch_id = watch_id;
    }
}
