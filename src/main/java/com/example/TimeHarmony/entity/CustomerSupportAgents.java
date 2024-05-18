package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerSupportAgents extends Members {
    private String report_id;

    public CustomerSupportAgents() {

    }

    public CustomerSupportAgents(String member_id, String member_image, String username, String password,
            String first_name, String last_name, int is_active, String address, String email, String phone,
            Timestamp last_login_date, Timestamp last_logout_date, String email_verification, int role_id,
            String report_id) {
        super(member_id, member_image, username, password, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification, role_id);
        this.report_id = report_id;
    }

    public CustomerSupportAgents(String report_id) {
        this.report_id = report_id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

}
