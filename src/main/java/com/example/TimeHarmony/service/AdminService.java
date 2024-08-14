package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.entity.Payment;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Staff;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.OrderState;
import com.example.TimeHarmony.enumf.RequestStatus;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.enumf.StaffRole;
import com.example.TimeHarmony.enumf.UserAuthenticationStatus;
import com.example.TimeHarmony.repository.AdminRepository;
import com.example.TimeHarmony.repository.AppraiseRequestRepository;
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
  @Autowired
  private AppraiseRequestRepository APPRAISE_REQUEST_REPOSITORY;

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
    String rs = ORDER_REPOSITORY.getWebProfit(); 

    if (rs == null) {
      return 0 ; 
    }
    return Float.parseFloat(rs); 
  }

  @Override
  public List<String> getAllShippingOrder() {
    return ORDER_REPOSITORY.getAllShippingOrder();
  }

  @Override
  public List<Payment> getAllFailOrder() {
    return PAYMENT_REPOSITORY.getAllFailOrder();
  }

  @Override
  public String changeStaffRole(String id, StaffRole role) {
    try {

      STAFF_REPOSITORY.updateStaffRole(role, UUID.fromString(id));
      return "Staff role changed to " + role.name();
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String assignAppraiser(String request_id, String aid, String date) {
    try {
      if (STAFF_REPOSITORY.getRole(UUID.fromString(aid)) != StaffRole.APPRAISER)
        throw new Exception("ID is not Appraiser");
      if (APPRAISE_REQUEST_REPOSITORY.checkAppraiser(request_id) != null)
        throw new Exception("Request is already assigned");
      if (APPRAISE_REQUEST_REPOSITORY.getStatus(request_id) != RequestStatus.NEW)
        throw new Exception("Logic Error");
      if (date == null || date.isEmpty())
        throw new Exception("Appointment date is required");

      APPRAISE_REQUEST_REPOSITORY.assignRequest(UUID.fromString(aid), request_id, Timestamp.valueOf(date));
      APPRAISE_REQUEST_REPOSITORY.updateStatus(RequestStatus.PROCESSING, request_id);

      return "Appraiser " + aid + " is assigned to request " + request_id;
    } catch (Exception e) {
      return e.toString();
    }

  }

  @Override
  public List<AppraiseRequest> getAllRequest() {
    try {
      APPRAISE_REQUEST_REPOSITORY.updateExpired();
      return APPRAISE_REQUEST_REPOSITORY.findAll();
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public List<Orders> getOrderByState(int state) {
    return ORDER_REPOSITORY.getOrderByState(state);
  }

  @Override
  public List<Staff> getStaffByRole(StaffRole role) {
    return STAFF_REPOSITORY.getStaffByRole(role);
  }

  @Override
  public String updateAssignAppraiser(String request_id, String aid, String date) {
    try {
      if (date == null || date.isEmpty())
        throw new Exception("Date is required");
      if (APPRAISE_REQUEST_REPOSITORY.getStatus(request_id) != RequestStatus.PROCESSING)
        throw new Exception("Logic Error");
      if (aid == null || aid.isEmpty()) {
        APPRAISE_REQUEST_REPOSITORY.updateAppraiserADate(null, null, request_id);
        return "Appraiser Unassgined";
      }
      APPRAISE_REQUEST_REPOSITORY.updateAppraiserADate(UUID.fromString(aid), Timestamp.valueOf(date), request_id);
      return "Request Updated";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public int orderOfDay(String date) {
    String rs = ORDER_REPOSITORY.NumOfOrderByDate(date); 

    if (rs == null){
      return 0 ; 
    }
    return Integer.parseInt(rs); 
  }

  @Override
  public int successOrderOfDay(String date) {
    String rs = ORDER_REPOSITORY.NumOfOrderSuccessByDate(date); 
    if (rs == null){
      return 0 ; 
    }
    return Integer.parseInt(rs); 
  }

  @Override
  public long totalAmountOrderOfDay(String date) {
    String rs = ORDER_REPOSITORY.totalPriceOrderByDate(date) ; 
    
    if(rs == null){
      return 0; 
    }
    return Long.parseLong(rs); 
  }

  @Override
  public long totalAmountSuccess(String date) {
    String rs = ORDER_REPOSITORY.totalPriceOrderSuccessByDate(date); 
    if(rs == null){
      return 0; 
    }
    return Long.parseLong(rs); 
  }

  @Override
  public List<Map<String, Integer>> top3Brand() {
    return WATCH_REPOSITORY.top3brand(); 
  }

  @Override
  public float  getWebProfitByDate(String from, String to) {
    String rs = ORDER_REPOSITORY.getWebProfitByDate(from, to); 

    if (rs == null) {
      return 0 ; 
    }
    return Float.parseFloat(rs); 
  }

  @Override
  public float getWebProfitByMonth(String fromM, String toM) {
    String rs = ORDER_REPOSITORY.getWebProfitByMonth(fromM, toM); 

    if (rs == null) {
      return 0 ; 
    }
    return Float.parseFloat(rs); 
  }

  @Override
  public int getSuccessOrderByMonth(String month) {
    String rs1 = ORDER_REPOSITORY.numATMcompleteOrder(month);
    String rs2 = ORDER_REPOSITORY.numCODcompleteOrder(month); 

    int num1, num2 ; 
    if(rs1 == null) {
      num1 = 0 ; 
    } else {
    num1 = Integer.parseInt(rs1); 
    }

    if(rs2 == null){
      num2 = 0 ; 
    } else {
    num2 = Integer.parseInt(rs2); 
    }

    return num1 + num2 ; 
    
  }

  @Override
  public long getTotalAmountSuccessOrderByMonth(String month) {
    String rs1 = ORDER_REPOSITORY.totalAmountATM(month); 
    String rs2 = ORDER_REPOSITORY.totalAmountCOD(month); 
    long num1, num2 ; 

    if(rs1 == null) {
      num1 = 0 ; 
    } else {
    num1 = Long.parseLong(rs1); 
    }
    

    if(rs2 == null) {
      num2 = 0 ; 
    } else {
    num2 = Long.parseLong(rs2); 
    }

    System.out.print(num1);
    System.out.print(",");
    System.out.print(num2);
    return num1 + num2; 

  }

  @Override
  public long getTotalProfitOrderByMonth(String month) {
    String rs1 = ORDER_REPOSITORY.totalProfitATM(month); 
    String rs2 = ORDER_REPOSITORY.totalProfitCOD(month); 
    long num1, num2 ; 

    if(rs1 == null) {
      num1 = 0 ; 
    } else {
    num1 = Long.parseLong(rs1); 
    }

    if(rs2 == null) {
      num2 = 0 ; 
    } else {
    num2 = Long.parseLong(rs2); 
    }
    System.out.print(num1);
    System.out.print(num2);
    return num1 + num2; 
  }

  @Override
  public List<Members> getMemberByState(int state) {
    return MEMBER_REPOSITORY.getMemberByState(state); 
  }

}
