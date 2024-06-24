package com.example.TimeHarmony.builder.interfacepack;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.TimeHarmony.entity.Cart;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface IMemberBuilder {
    public IMemberBuilder setMemberId(UUID id);

    public IMemberBuilder setGoogle(String google_id);

    public IMemberBuilder setUserLogInfo(Users user);

    public IMemberBuilder setMemberImage(String url);

    public IMemberBuilder setFirstName(String fname);

    public IMemberBuilder setLastName(String lname);

    public IMemberBuilder setActive(int is_active);

    public IMemberBuilder setEmail(String email);

    public IMemberBuilder setPhone(String phone);

    public IMemberBuilder setLastLoginDate(Timestamp last_login);

    public IMemberBuilder setLastLogoutDate(Timestamp last_logout);

    public Members build();
}
