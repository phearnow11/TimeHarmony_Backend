package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;

public interface IMemberService {

    public Optional<Members> getMemberbyID(String member_id);

    public List<Addresses> getAddresses();

}
