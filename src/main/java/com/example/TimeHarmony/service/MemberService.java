package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.dtos.AccessHistory;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AddressRepository;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.SellerRepository;
import com.example.TimeHarmony.repository.UsersRepository;
import com.example.TimeHarmony.service.interfacepack.IMemberService;

@Service
public class MemberService implements IMemberService {

    private final byte DEFAULT_ACTIVE_STATUS = 1;
    private final byte DEFAULT_INACTIVE_STATUS = 0;

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;
    @Autowired
    private UsersRepository USER_REPOSOTORY;
    @Autowired
    private AuthoritiesRepository AUTHORITIES_REPOSITORY;
    @Autowired
    private AddressRepository ADDRESS_REPOSITORY;
    @Autowired
    private SellerRepository SELLER_REPOSITORY;

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
    public boolean isExist(Users user, String email) {
        boolean res = USER_REPOSOTORY.existsById(user.getUsername());
        List<Members> members = MEMBER_REPOSITORY.getMemberbyEmail(email);
        if (members.isEmpty() && !res)
            return false;
        return true;
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

    @Override
    public void login(String id) {
        MEMBER_REPOSITORY.updateLastLoginDate(Timestamp.valueOf(LocalDateTime.now()), UUID.fromString(id));
        MEMBER_REPOSITORY.updateActiveStatus(DEFAULT_ACTIVE_STATUS, UUID.fromString(id));
    }

    @Override
    public void logout(String id) {
        MEMBER_REPOSITORY.updateLastLogoutDate(Timestamp.valueOf(LocalDateTime.now()), UUID.fromString(id));
        MEMBER_REPOSITORY.updateActiveStatus(DEFAULT_INACTIVE_STATUS, UUID.fromString(id));
    }

    @Override
    public String changeUserPassword(String username, String new_password) {
        USER_REPOSOTORY.updateUserPassword(passwordEncoder.encode(new_password), username);
        return new_password;
    }

    @Override
    public String updateEmail(String member_id, String new_email) {
        MEMBER_REPOSITORY.updateMemberEmail(new_email, UUID.fromString(member_id));
        return new_email;
    }

    @Override
    public Report createReport(Report report) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReport'");
    }

    @Override
    public List<AccessHistory> getAllAccessHistories(String member_id) {
        return MEMBER_REPOSITORY.getAllAccessHistoriesFromMember(UUID.fromString(member_id));
    }

    @Override
    public String updateAccessHistories(String member_id, List<String> urls, List<Timestamp> times) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccessHistories'");
    }

    @Override
    public Addresses addAddress(Addresses address) {
        return ADDRESS_REPOSITORY.save(address);
    }

    @Override
    public String toSeller(String m_id, String username) {
        try {
            SELLER_REPOSITORY.toSeller(UUID.fromString(m_id));
            AUTHORITIES_REPOSITORY.updateRole(Roles.ROLE_SELLER.name(), username);
        } catch (Exception e) {
            return "Seller is already existed";
        }
        return "Success";
    }

}
