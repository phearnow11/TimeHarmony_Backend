package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.AddressesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IMemberService;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;
    @Autowired
    private AddressesRepository ADDRESS_REPOSITORY;
    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public Optional<Users> getMemberbyID(String member_id) {
        if (member_id.isEmpty())
            return null;
        Optional<Users> member = Optional.empty();
        member = MEMBER_REPOSITORY.findById(UUID.fromString(member_id));
        if (member.isPresent())
            return member;
        return null;
    }

    @Override
    public List<Addresses> getAddresses() {
        return ADDRESS_REPOSITORY.findAll();
    }

    @Override
    public Optional<Watch> getWatchByGender(String gender) {
        if (gender.isEmpty())
            return null;
        Optional<Watch> watch = Optional.empty();
        watch = Optional.of(WATCH_REPOSITORY.findWatchesByGender(gender));
        if (watch.isPresent())
            return watch;
        return null;
    }

    @Override
    public Users saveUser(Users user) {
        return MEMBER_REPOSITORY.save(user);
    }

}
