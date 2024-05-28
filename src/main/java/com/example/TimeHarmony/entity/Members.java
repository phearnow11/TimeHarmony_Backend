package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Members")
public class Members {
    @Id
    @GeneratedValue
    private UUID member_id;

    @OneToOne
    @JoinColumn(name = "username")
    private Users user_log_info;

    private String member_image;

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

    public Members() {
    }

    public Members(UUID member_id, Users user_log_info, String member_image, String first_name, String last_name,
            int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification) {
        this.member_id = member_id;
        this.user_log_info = user_log_info;
        this.member_image = member_image;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_active = is_active;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.last_login_date = last_login_date;
        this.last_logout_date = last_logout_date;
        this.email_verification = email_verification;
    }

    @Override
    public String toString() {
        return "Members [member_id=" + member_id + ", member_image=" + member_image + ", first_name=" + first_name
                + ", last_name=" + last_name + ", is_active=" + is_active + ", address=" + address + ", email=" + email
                + ", phone=" + phone + ", last_login_date=" + last_login_date + ", last_logout_date=" + last_logout_date
                + ", email_verification=" + email_verification + "]";
    }

    public String getMember_image() {
        return member_image;
    }

    public void setMember_image(String member_image) {
        this.member_image = member_image;
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

    public UUID getMember_id() {
        return member_id;
    }

    public void setMember_id(UUID member_id) {
        this.member_id = member_id;
    }

    public Users getUser_log_info() {
        return user_log_info;
    }

    public void setUser_log_info(Users user_log_info) {
        this.user_log_info = user_log_info;
    }

}
