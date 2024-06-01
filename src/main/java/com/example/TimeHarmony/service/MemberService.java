package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Authorities;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AddressesRepository;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.UsersRepository;
import com.example.TimeHarmony.service.interfacepack.IMemberService;

@Service
public class MemberService implements IMemberService {

    private final byte DEFAULT_ACTIVE_STATUS = 1;
    private final byte DEFAULT_INACTIVE_STATUS = 0;

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;
    @Autowired
    private AddressesRepository ADDRESS_REPOSITORY;
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
        System.out.println(members);
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
    public String autoVerificationCodeGenerate() {
        int leftlimit = 48, rightlimit = 122, stringlength = 6;
        String code = "";
        Random random = new Random();
        code = random.ints(leftlimit, rightlimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(stringlength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return code;
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
    public String updateEmailCode(String member_id) {
        String code = autoVerificationCodeGenerate();
        MEMBER_REPOSITORY.updateEmailVerificationCode(code, UUID.fromString(member_id));
        return code;
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

}
