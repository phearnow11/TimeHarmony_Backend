package com.example.TimeHarmony.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Payment;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.OrderState;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.enumf.UserAuthenticationStatus;
import com.example.TimeHarmony.repository.AdminRepository;
import com.example.TimeHarmony.repository.AuthoritiesRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.OrderRepository;
import com.example.TimeHarmony.repository.PaymentRepository;
import com.example.TimeHarmony.repository.ReportRepository;
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
  private OrderRepository ORDER_REPOSITORY;

  @Autowired
  private PaymentRepository PAYMENT_REPOSITORY;

  @Autowired
  private OrderService ORDER_SERVICE;

  @Autowired
  private ReportRepository REPORT_REPOSITY; 

  @Override
  public List<Members> getMembers() {
    return MEMBER_REPOSITORY.getAllMemberExclusive(UUID.fromString("54b0e42b-f72b-4edb-b967-2c421285fcda"));
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
  public String deleteWatch(String id) {
    try {

      WATCH_REPOSITORY.deleteById(id);
      return "Watch deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String deleteMemberbyId(String id) {
    try {
      MEMBER_REPOSITORY.deleteById(UUID.fromString(id));
      return "User deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String banMemberbyId(String username) {
    try {
      int DELETE_WATCH = 2;
      USER_REPOSOTORY.updateUserState(UserAuthenticationStatus.BANNED.getValue(), username);
      List<String> orders = ORDER_REPOSITORY.getOrderFromSeller(username);
      orders.removeAll(Collections.singleton(null));
      List<OrderState> states = ORDER_REPOSITORY.getStatesFromOrder(orders);
      System.out.println(orders + " - " + states);
      for (int i = 0; i < orders.size(); i++) {
        if (states.get(i) == OrderState.PENDING)
          ORDER_SERVICE.cancelOrder(orders.get(i));

      }
      WATCH_REPOSITORY.adminOperationWithAllWatchFromSeller(username, DELETE_WATCH);
      return "User banned";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String unbanMemberbyId(String username) {
    try {
      int VIEW_WATCH = 1;
      USER_REPOSOTORY.updateUserState(UserAuthenticationStatus.ACTIVE.getValue(), username);
      WATCH_REPOSITORY.adminOperationWithAllWatchFromSeller(username, VIEW_WATCH);
      return "User unbanned";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Map<String, String>> viewWatchCreationHistory() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'viewWatchCreateHistory'");
  }

  @Override
  public List<Report> viewReports() {
    return REPORT_REPOSITY.findAll(); 
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
  public String addWatches(List<Watch> w, String s_id) throws Exception {
    try {
      Sellers s = SELLER_SERVICE.getSellerbyId(s_id);
      System.out.println(s);
      for (Watch i : w) {
        SELLER_SERVICE.createWatch(i, s);
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
        Members m = MEMBER_SERVICE.saveUser(new MemberBuilder()
            .setMemberImage("https://files.catbox.moe/n1w3b0.png").setUserLogInfo(tu).build(), tu);
        System.out.println(MEMBER_SERVICE.toSeller(m.getMember_id().toString(), TEST_USERNAME));
        System.out.println("Test User Created -- User id : " + m.getMember_id());
        return m.getMember_id().toString();
      }
      Members m = MEMBER_SERVICE.getMemberbyUserLogInfo(MEMBER_SERVICE.getUserbyUsername(TEST_USERNAME));
      System.out.println("Test User is already created --> User id : " + m.getMember_id());
      return m.getMember_id().toString();
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public float getProfit() {
    return ORDER_REPOSITORY.getWebProfit();
  }

  @Override
  public List<String> getAllShippingOrder() {
    return ORDER_REPOSITORY.getAllShippingOrder();
  }

@Override
public List<Payment> getAllFailOrder() {
    return PAYMENT_REPOSITORY.getAllFailOrder(); 
}

}
