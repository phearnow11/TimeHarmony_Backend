package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appraisers")
public class Appraisers extends Users {

    private String watch_id;
    private String year_experience;

    public Appraisers(String member_id, String member_image, String username, String password, String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, int role_id, boolean enable, String watch_id,
            String year_experience) {
        super(member_id, member_image, username, password, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification, role_id, enable);
        this.watch_id = watch_id;
        this.year_experience = year_experience;
    }

    public Appraisers() {
    }

    public String getYear_experience() {
        return year_experience;
    }

    public void setYear_experience(String year_experience) {
        this.year_experience = year_experience;
    }

    public String getWatch_id() {
        return watch_id;
    }

    public void setWatch_id(String watch_id) {
        this.watch_id = watch_id;
    }
}
