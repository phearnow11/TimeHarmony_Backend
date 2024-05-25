package com.example.TimeHarmony.builder;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.TimeHarmony.entity.Users;

public interface IMemberBuilder {
    public IMemberBuilder setMemberId(UUID id);

    public IMemberBuilder setMemberImage(String url);

    public IMemberBuilder setUsername(String username);

    public IMemberBuilder setPassword(String pwd);

    public IMemberBuilder setFirstName(String fname);

    public IMemberBuilder setLastName(String lname);

    public IMemberBuilder setActive(int is_active);

    public IMemberBuilder setDefaultAddress(String address);

    public IMemberBuilder setEmail(String email);

    public IMemberBuilder setPhone(String phone);

    public IMemberBuilder setLastLoginDate(Timestamp last_login);

    public IMemberBuilder setLastLogoutDate(Timestamp last_logout);

    public IMemberBuilder setEmailVerify(String code);

    public IMemberBuilder setRole(int role_id);

    public IMemberBuilder setEnabled(boolean status);

    public Users build();
}
