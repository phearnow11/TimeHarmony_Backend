package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Appraisers")
@PrimaryKeyJoinColumn(name = "member_id")
public class Appraisers extends Members {

    private String watch_id;
    private String year_experience;

    public Appraisers(UUID member_id, String google_id, Users user_log_info, String member_image, String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, String watch_id, String year_experience) {
        super(member_id, google_id, user_log_info, member_image, first_name, last_name, is_active, address, email,
                phone, last_login_date, last_logout_date, email_verification);
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
