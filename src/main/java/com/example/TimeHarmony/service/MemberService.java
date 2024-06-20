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
import com.example.TimeHarmony.dtos.Favorites;
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
    @Autowired
    private StringService STRING_SERVICE;

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
        try {
            Members m = MEMBER_REPOSITORY.findById(UUID.fromString(member_id)).get();
            return ADDRESS_REPOSITORY.getAddresses(m);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String deleteMember(String id) {
        try {
            MEMBER_REPOSITORY.deleteById(UUID.fromString(id));
            return "Member deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Members saveUser(Members member, Users logInfo) {
        Authorities auth = new Authorities();
        if (MEMBER_REPOSITORY.findAll().isEmpty()) {
            auth = new Authorities(logInfo.getUsername(), Roles.ROLE_ADMIN.name());
        } else
            auth = new Authorities(logInfo.getUsername(), Roles.ROLE_USER.name());
        logInfo.setPassword(passwordEncoder.encode(logInfo.getPassword()));
        USER_REPOSOTORY.save(logInfo);
        AUTHORITIES_REPOSITORY.save(auth);
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
        try {
            for (int i = 0; i < urls.size(); i++) {
                MEMBER_REPOSITORY.insertAccessHistory(UUID.fromString(member_id), urls.get(i), times.get(i));
            }
            return "Access History updated !";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Addresses addAddress(Addresses address) {
        String a_id = "A" + STRING_SERVICE.autoGenerateString(11);
        address.setAddress_id(a_id);
        if (address.is_default()) {
            Optional<Addresses> ad = ADDRESS_REPOSITORY.checkDefault(address.getMember(), true);
            if (ad.isPresent()) {
                ADDRESS_REPOSITORY.updateDefault(false, ad.get().getAddress_id());
            }
        }
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
        return "Member to Seller Success";
    }

    @Override
    public String addFavorites(String m_id, List<String> w_ids) {
        try {
            for (String i : w_ids) {
                MEMBER_REPOSITORY.insertFavorites(UUID.fromString(m_id), i);
            }
            return "Favorites added";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String deleteFavorites(String m_id, List<String> w_ids) {
        try {
            for (String i : w_ids)
                MEMBER_REPOSITORY.deleteFavorites(i, UUID.fromString(m_id));
            return "Favorites deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public List<Favorites> getFavoritesFromMember(String m_id) {
        try {
            return MEMBER_REPOSITORY.getFavoritesFromMember(UUID.fromString(m_id));
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String deleteAddress(String m_id, String a_id) {
        try {
            ADDRESS_REPOSITORY.deleteAddress(UUID.fromString(m_id), a_id);
            return "Address deleted";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public Addresses getDefaultAddress(String m_id) {
        try {
            Members m = getMemberbyID(m_id).get();
            Optional<Addresses> add = ADDRESS_REPOSITORY.checkDefault(m, true);
            if (add.isPresent())
                return add.get();
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public String updateMemberImage(String id, String url) {
        try {
            MEMBER_REPOSITORY.updateMemberImage(url, UUID.fromString(id));
            return "Image updated";
        } catch (Exception e) {
            return e.toString();
        }
    }

}
