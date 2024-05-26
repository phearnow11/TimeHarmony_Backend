package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Optional<Watch> getWatchBySeries(String series) {
        if (series.isEmpty())
        return null;
    Optional<Watch> watch = Optional.empty();
    watch = Optional.of(WATCH_REPOSITORY.findWatchesBySeries(series));
    if (watch.isPresent())
            return watch;
        return null;
    }

    @Override
    public Optional<Watch> getWatchByBrand(String brand) {
        if (brand.isEmpty())
            return null;
        Optional<Watch> watch = Optional.empty();
        watch = Optional.of(WATCH_REPOSITORY.findWatchesByBrand(brand));
        if (watch.isPresent())
            return watch;
        return null;
    }

    @Override
    public Optional<Watch> getWatchByStyle(String style) {
        if (style.isEmpty())
            return null;
        Optional<Watch> watch = Optional.empty();
        watch = Optional.of(WATCH_REPOSITORY.findWatchesByStyle(style));
        if (watch.isPresent())
            return watch;
        return null;
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

    

}
