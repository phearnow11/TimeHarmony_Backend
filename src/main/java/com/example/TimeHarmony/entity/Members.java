package com.example.TimeHarmony.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Members {
    @Id
    private String member_id;
    private String member_name;
    private String address;
    private String email;
    private String phone;

    @Temporal(TemporalType.DATE)
    private Date last_login_date;

    private int role_id;

    public Date getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Date last_login_date) {
        this.last_login_date = last_login_date;
    }

    public Members(String member_id, String member_name, String address, String email, String phone,
            Date last_login_date, int role_id) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.last_login_date = last_login_date;
        this.role_id = role_id;
    }

    public Members(String member_id, String member_name, String address, String email, String phone, int role_id) {
        this.member_id = member_id;
        this.member_name = member_name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.role_id = role_id;
    }

    public Members() {
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }
}
