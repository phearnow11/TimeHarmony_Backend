package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer_Support_Agents")
public class CustomerSupportAgents extends Members {

    private String report_id;

    public CustomerSupportAgents() {
    }

    public CustomerSupportAgents(UUID member_id, String username, String member_image, String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, String report_id) {
        super(member_id, username, member_image, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification);
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
