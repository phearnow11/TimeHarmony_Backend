package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AddressesRepository;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.UsersRepository;
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
    @Autowired
    private UsersRepository USER_REPOSOTORY;
    @Autowired
    private AuthoritiesRepository AUTHORITIES_REPOSITORY;

    private final PasswordEncoder passwordEncoder;

    public MemberService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<Members> getMemberbyID(String member_id) {
        if (member_id.isEmpty())
            return null;
        Optional<Members> member = Optional.empty();
        member = MEMBER_REPOSITORY.findById(UUID.fromString(member_id));
        if (member.isPresent())
            return member;
        return null;
    }

    @Override
    public List<Addresses> getAddresses(String member_id) {
        return ADDRESS_REPOSITORY.getAddressesbyMember(member_id);
    }

    @Override
    public List<Watch> getWatchByGender(String gender) {
        return WATCH_REPOSITORY.findWatchesByGender(gender);
    }

    @Override
    public List<Watch> getWatchBySeries(String series) {
        return WATCH_REPOSITORY.findWatchesBySeries(series);
    }

    @Override
    public List<Watch> getWatchByBrand(String brand) {
        return WATCH_REPOSITORY.findWatchesByBrand(brand);
    }

    @Override
    public List<Watch> getWatchByStyle(String style) {
        return WATCH_REPOSITORY.findWatchesByStyle(style);
    }

    @Override
    public Members saveUser(Members member, Users logInfo) {
        logInfo.setPassword(passwordEncoder.encode(logInfo.getPassword()));
        USER_REPOSOTORY.save(logInfo);
        AUTHORITIES_REPOSITORY.save(new Authorities(logInfo.getUsername(), Roles.ROLE_USER.name()));
        return MEMBER_REPOSITORY.save(member);
    }

    @Override
    public boolean isExist(Users user) {
        return USER_REPOSOTORY.existsById(user.getUsername());
    }

    @Override
    public List<Watch> getListWatchDECS() {
        return WATCH_REPOSITORY.findAll(Sort.by(Sort.Direction.DESC, "watch_id"));
    }

    @Override
    public Members getMemberbyUserLogInfo(Users userLogInfo) {
        Optional<Members> member = MEMBER_REPOSITORY.getMemberbyUserLogInfo(userLogInfo);
        if (member.isPresent())
            return member.get();
        return null;
    }

    @Override
    public Users getUserbyUsername(String username) {
        Optional<Users> user = Optional.empty();
        user = USER_REPOSOTORY.getUserbyUsername(username);
        if (user.isPresent())
            return user.get();
        return null;
    }

}
