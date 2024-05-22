package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Users;

public interface IMemberService {

    public Optional<Users> getMemberbyID(String member_id);

    public List<Addresses> getAddresses();

}
