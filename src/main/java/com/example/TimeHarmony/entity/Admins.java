package com.example.TimeHarmony.entity;

import java.sql.Date;

import jakarta.persistence.Entity;

@Entity
public class Admins extends Members {

    public Admins() {
    }

    public Admins(String member_id, String username, String password, String member_name, String address, String email,
            String phone, Date last_login_date, int role_id) {
        super(member_id, username, password, member_name, address, email, phone, last_login_date, role_id);
    }
}
