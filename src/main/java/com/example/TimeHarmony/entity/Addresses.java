package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Addresses {
    @Id
    private String address_id;
    private String member_id;
    private String phone;
    private String address_detail;
    private byte address_type;
    private byte is_default;

    public Addresses(String address_id, String member_id, String phone, String address_detail, byte address_type,
            byte is_default) {
        this.address_id = address_id;
        this.member_id = member_id;
        this.phone = phone;
        this.address_detail = address_detail;
        this.address_type = address_type;
        this.is_default = is_default;
    }

    public Addresses() {
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getPhone() {
        return phone;
    }

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
