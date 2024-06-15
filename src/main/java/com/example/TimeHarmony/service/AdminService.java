package com.example.TimeHarmony.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.repository.AdminRepository;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.StaffRepository;
import com.example.TimeHarmony.repository.UsersRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IAdminService;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Autowired
    private AdminRepository ADMIN_REPOSITORY;

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Autowired
    private StaffRepository STAFF_REPOSITORY;

    @Autowired
    private AuthoritiesRepository AUTHORITIES_REPOSITORY;

    @Autowired
    private UsersRepository USER_REPOSOTORY;

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Autowired
    private SellerService SELLER_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    @Override
    public List<Members> getMembers() {
        return MEMBER_REPOSITORY.findAll();
    }

    @Override
    public List<Admins> getAdmins() {
        return ADMIN_REPOSITORY.findAll();
    }

    @Override
    public List<Watch> getWatches() {
        return WATCH_REPOSITORY.findAll();
    }

    @Override
    public void deleteWatch(String id) {
        WATCH_REPOSITORY.deleteById(id);
    }

    @Override
    public void deleteMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMemberbyId'");
    }

    @Override
    public void banMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'banMemberbyId'");
    }

    @Override
    public void unbanMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unbanMemberbyId'");
    }

    @Override
    public List<Watch> viewWatchCreationHistory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewWatchCreateHistory'");
    }

    @Override
    public List<Report> viewReports() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewReports'");
    }

    @Override
    public String toStaff(String id, String username) {
        try {

            STAFF_REPOSITORY.toStaff(UUID.fromString(id));
            AUTHORITIES_REPOSITORY.updateRole(Roles.ROLE_STAFF.name(), username);
        } catch (Exception e) {
            return "Staff is already promoted!";
        }
        return "To staff success!";
    }

    @Override
    public String addMembers(List<Members> m) {
        try {
            for (Members i : m) {
                MEMBER_SERVICE.saveUser(i, i.getUser_log_info());
            }
            return "Succeed !";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String addWatches(List<Watch> w) throws Exception {
        try {
            String s_id = STRING_SERVICE.stringSpaceSplit(testUser())
                    .get(STRING_SERVICE.stringSpaceSplit(testUser()).size() - 1);
            for (Watch i : w) {
                SELLER_SERVICE.createWatch(i, s_id);
            }
            return "Succeed !";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String testUser() {

        String TEST_USERNAME = "test";
        byte enabled = 1;
        Users tu = new Users(TEST_USERNAME, "password", null, enabled);

        try {
            if (!USER_REPOSOTORY.existsById(TEST_USERNAME)) {
                System.out.println("Test User not found");
                Members m = MEMBER_SERVICE.saveUser(new MemberBuilder().setUserLogInfo(tu).build(), tu);
                System.out.println(MEMBER_SERVICE.toSeller(m.getMember_id().toString(), TEST_USERNAME));
                return "Test User Created -- User id : " + m.getMember_id();
            }
            Members m = MEMBER_SERVICE.getMemberbyUserLogInfo(MEMBER_SERVICE.getUserbyUsername(TEST_USERNAME));
            return "Test User is already created --> User id : " + m.getMember_id();
        } catch (Exception e) {
            return e.toString();
        }
    }

}
