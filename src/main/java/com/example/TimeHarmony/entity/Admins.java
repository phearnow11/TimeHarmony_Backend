package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;

@Entity
public class Admins extends Members {

    private String key_pass;

    public Admins() {
    }

    public Admins(String member_id, String key_pass, String member_image, String username, String password,
            String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, int role_id) {

        super(member_id, member_image, username, password, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification, role_id);
        this.key_pass = key_pass;
    }

    public String getKey_pass() {
        return key_pass;
    }

    public void setKey_pass(String key_pass) {
        this.key_pass = key_pass;
    }

    
}
