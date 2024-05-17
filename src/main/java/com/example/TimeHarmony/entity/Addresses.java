package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;

@Entity
public class Addresses extends Members{
    private String address_id ; 
    private String phone ; 
    private String address_detail ; 
    private byte address_type ; 
    private byte is_default ;


    public Addresses(String member_id, String member_image, String username, String password, String first_name,
            String last_name, int is_active, String address, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, String email_verification, int role_id, String address_id, String phone2,
            String address_detail, byte address_type, byte is_default) {
        super(member_id, member_image, username, password, first_name, last_name, is_active, address, email, phone,
                last_login_date, last_logout_date, email_verification, role_id);
        this.address_id = address_id;
        phone = phone2;
        this.address_detail = address_detail;
        this.address_type = address_type;
        this.is_default = is_default;
    } 

    public String getAddress_id() {
        return address_id;
    }


    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }


    @Override
    public String getPhone() {
        return phone;
    }


    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getAddress_detail() {
        return address_detail;
    }


    public void setAddress_detail(String address_detail) {
        this.address_detail = address_detail;
    }


    public byte getAddress_type() {
        return address_type;
    }


    public void setAddress_type(byte address_type) {
        this.address_type = address_type;
    }


    public byte getIs_default() {
        return is_default;
    }


    public void setIs_default(byte is_default) {
        this.is_default = is_default;
    }

}
