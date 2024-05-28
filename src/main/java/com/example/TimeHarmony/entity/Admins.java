package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "member_id")
public class Admins extends Members {

    private String key_pass;

    public Admins() {
    }

    public Admins(UUID member_id, Users user_log_info, String member_image, String first_name, String last_name,
            int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, String key_pass) {
        super(member_id, user_log_info, member_image, first_name, last_name, is_active, address, email, phone,
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
