package com.example.TimeHarmony.entity;

import java.sql.Date;
import java.sql.Timestamp;

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
    private String member_image;
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private int is_active;
    private String address;
    private String email;
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp last_login_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp last_logout_date;

    private String email_verification;
    private int role_id;

    public Members(String member_id, String member_image, String username, String password, String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, int role_id) {
        this.member_id = member_id;
        this.member_image = member_image;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_active = is_active;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.last_login_date = last_login_date;
        this.last_logout_date = last_logout_date;
        this.email_verification = email_verification;
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

    public String getMember_image() {
        return member_image;
    }

    public void setMember_image(String member_image) {
        this.member_image = member_image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
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

    public Timestamp getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(Timestamp last_login_date) {
        this.last_login_date = last_login_date;
    }

    public Timestamp getLast_logout_date() {
        return last_logout_date;
    }

    public void setLast_logout_date(Timestamp last_logout_date) {
        this.last_logout_date = last_logout_date;
    }

    public String getEmail_verification() {
        return email_verification;
    }

    public void setEmail_verification(String email_verification) {
        this.email_verification = email_verification;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

}
