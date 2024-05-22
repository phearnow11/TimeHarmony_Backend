package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.repository.AddressesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.service.interfacepack.IMemberService;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;
    @Autowired
    private AddressesRepository ADDRESS_REPOSITORY;

    @Override
    public Optional<Users> getMemberbyID(String member_id) {
        if (member_id.isEmpty())
            return null;
        Optional<Users> member = Optional.empty();
        member = MEMBER_REPOSITORY.findById(member_id);
        if (member.isPresent())
            return member;
        return null;
    }

    @Override
    public List<Addresses> getAddresses() {
        return ADDRESS_REPOSITORY.findAll();
    }

}
