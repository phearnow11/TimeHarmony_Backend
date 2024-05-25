package com.example.TimeHarmony.builder;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.TimeHarmony.entity.Users;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class MemberBuilder implements IMemberBuilder {
    private UUID member_id;
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
    private boolean enabled;

    @Override
    public IMemberBuilder setMemberId(UUID id) {
        this.member_id = id;
        return this;
    }

    @Override
    public IMemberBuilder setMemberImage(String url) {
        this.member_image = url;
        return this;
    }

    @Override
    public IMemberBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public IMemberBuilder setPassword(String pwd) {
        this.password = pwd;
        return this;
    }

    @Override
    public IMemberBuilder setFirstName(String fname) {
        this.first_name = fname;
        return this;
    }

    @Override
    public IMemberBuilder setLastName(String lname) {
        this.last_name = lname;
        return this;
    }

    @Override
    public IMemberBuilder setActive(int is_active) {
        this.is_active = is_active;
        return this;
    }

    @Override
    public IMemberBuilder setDefaultAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public IMemberBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public IMemberBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public IMemberBuilder setLastLoginDate(Timestamp last_login) {
        this.last_login_date = last_login;
        return this;
    }

    @Override
    public IMemberBuilder setLastLogoutDate(Timestamp last_logout) {
        this.last_logout_date = last_logout;
        return this;
    }

    @Override
    public IMemberBuilder setRole(int role_id) {
        this.role_id = role_id;
        return this;
    }

    @Override
    public IMemberBuilder setEmailVerify(String code) {
        this.email_verification = code;
        return this;
    }

    @Override
    public IMemberBuilder setEnabled(boolean status) {
        this.enabled = status;
        return this;
    }

    @Override
    public Users build() {
        return new Users(member_id, member_image, username, password, first_name, last_name, is_active, address, email,
                phone, last_login_date, last_logout_date, email_verification, role_id, enabled);
    }
}
