package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;

@Entity
public class Admins extends Members {

    private String key_pass;

    public Admins() {
    }

    public Admins(UUID member_id, String username, String member_image, String first_name, String last_name,
            int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, String key_pass) {
        super(member_id, username, member_image, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification);
        this.key_pass = key_pass;
    }

    public String getKey_pass() {
        return key_pass;
    }

    public void setKey_pass(String key_pass) {
        this.key_pass = key_pass;
    }

}
