package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.builder.WatchBuilder;
import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.entity.Payment;
import com.example.TimeHarmony.entity.Staff;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.enumf.StaffRole;
import com.example.TimeHarmony.service.AdminService;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.OrderService;
import com.example.TimeHarmony.service.PaymentService;
import com.example.TimeHarmony.service.ReportService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {

  @Autowired
  private AdminService ADMIN_SERVICE;

  @Autowired
  private MemberService MEMBER_SERVICE;

  @Autowired
  private WatchService WATCH_SERVICE;

  @Autowired
  private OrderService ORDER_SERVICE;

  @Autowired
  private ReportService REPORT_SERVICE;

  @Autowired
  private PaymentService PAYMENT_SERVICE;

  @RequestMapping(value = "get/members", method = RequestMethod.GET)
  public List<Members> getaMembers() {
    return ADMIN_SERVICE.getMembers();
  }

  @RequestMapping(value = "get/admins", method = RequestMethod.GET)
  public List<Admins> getAdmins() {
    return ADMIN_SERVICE.getAdmins();
  }

  @RequestMapping(value = "get/watches", method = RequestMethod.GET)
  public List<Watch> getWatches() {
    return ADMIN_SERVICE.getWatches();
  }

  @RequestMapping(value = "role/upgrade/staff/{id}", method = RequestMethod.POST)
  public String toStaff(@PathVariable("id") String id) {
    Members m = MEMBER_SERVICE.getMemberbyID(id).get();
    if (!m.getUser_log_info().getAuthorities().getAuthority().matches(Roles.ROLE_USER.name()))
      return "Only member can be upgrade!";
    return ADMIN_SERVICE.toStaff(id, m.getUser_log_info().getUsername());
  }

  @RequestMapping(value = "add/members", method = RequestMethod.POST)
  public String addMembers(@RequestBody List<Map<String, String>> data) {
    List<Members> ms = new ArrayList<>();
    for (Map<String, String> i : data) {

      Map<String, String> detail_info = i;

      byte a = 1;
      Users logInfo = new Users(detail_info.get("username"), detail_info.get("password"), null, a);

      Members m = new MemberBuilder()
          .setUserLogInfo(logInfo)
          .setFirstName(detail_info.get("Fname"))
          .setLastName(detail_info.get("Lname"))
          .setActive(0)
          .setPhone(detail_info.get("phone"))
          .setEmail(detail_info.get("email"))
          .build();
      ms.add(m);

    }
    return ADMIN_SERVICE.addMembers(ms);
  }

  @RequestMapping(value = "add/watches", method = RequestMethod.POST)
  public String addWatches(@RequestBody List<Map<String, String>> datas) {

    List<Watch> ws = new ArrayList<>();
    byte DEFAULT_STATUS = 0;

    for (Map<String, String> data : datas) {
      Watch watch = new WatchBuilder()
          .setWatchId(WATCH_SERVICE.generateWatchId())
          .setWatchImage(new ArrayList<>())
          .setWatchDescription(data.get("description"))
          .setWatchName(data.get("name"))
          .setWatchCreateDate(Timestamp.valueOf(LocalDateTime.now()))
          .setState(DEFAULT_STATUS)
          .setPrice(Float.parseFloat(data.get("price")))
          .setBrand(data.get("brand"))
          .setSeries(data.get("series"))
          .setModel(data.get("model"))
          .setGender(data.get("gender"))
          .setStyleType(data.get("style"))
          .setSubClass(data.get("subclass"))
          .setMadeLabel(data.get("madelabel"))
          .setCalender(data.get("calender"))
          .setFeature(data.get("feature"))
          .setMovement(data.get("movement"))
          .setFunctions(data.get("function"))
          .setEngine(data.get("engine"))
          .setWaterResistant(data.get("waterresistant"))
          .setBandColor(data.get("bandcolor"))
          .setBandType(data.get("bandtype"))
          .setClasp(data.get("clasp"))
          .setBracelet(data.get("bracelet"))
          .setDialType(data.get("dialtype"))
          .setDialColor(data.get("dialcolor"))
          .setCrystal(data.get("crystal"))
          .setSecondMakers(data.get("secondmaker"))
          .setBezel(data.get("bezel"))
          .setBezelMaterial(data.get("bezelmaterial"))
          .setCaseBack(data.get("caseback"))
          .setCaseDimension(data.get("casedimension"))
          .setCaseShape(data.get("caseshape"))
          .build();
      ws.add(watch);
    }
    try {
      String s_id = ADMIN_SERVICE.testUser();
      return ADMIN_SERVICE.addWatches(ws, s_id);
    } catch (Exception e) {
      return e.toString();
    }
  }

  @RequestMapping(value = "get/orders", method = RequestMethod.GET)
  public List<Orders> getOrders() {
    return ORDER_SERVICE.getOrders();
  }

  @RequestMapping(value = "get/profit", method = RequestMethod.GET)
  public float getProfit() {
    return ADMIN_SERVICE.getProfit();
  }

  @RequestMapping(value = "ban/{username}", method = RequestMethod.POST)
  public String banUser(@PathVariable("username") String username) {
    return ADMIN_SERVICE.banMemberbyId(username);
  }

  @RequestMapping(value = "unban/{username}", method = RequestMethod.POST)
  public String unbanUser(@PathVariable("username") String username) {
    return ADMIN_SERVICE.unbanMemberbyId(username);
  }

  @RequestMapping(value = "get/shipping-order/all", method = RequestMethod.GET)
  public List<String> getAllShippingOrder() {
    return ADMIN_SERVICE.getAllShippingOrder();
  }

  @RequestMapping(value = "update/staff-role", method = RequestMethod.PUT)
  public String updateStaffRole(@RequestParam("id") String id, @RequestParam("role") StaffRole role) {
    return ADMIN_SERVICE.changeStaffRole(id, role);
  }

  @RequestMapping(value = "get/refund-payment", method = RequestMethod.GET)
  public List<Payment> getRefundMember() {
    return ADMIN_SERVICE.getAllFailOrder();
  }

  @RequestMapping(value = "get/requests", method = RequestMethod.GET)
  public List<AppraiseRequest> getRequests() {
    return ADMIN_SERVICE.getAllRequest();
  }

  @RequestMapping(value = "assign/request", method = RequestMethod.PATCH)
  public String assignRequest(@RequestParam("request_id") String request_id, @RequestParam("appraiser_id") String aid,
      @RequestBody Map<String, Object> data) {
    return ADMIN_SERVICE.assignAppraiser(request_id, aid, data.get("appointment_date").toString());
  }

  @RequestMapping(value = "get/orders-by-state/{state}", method = RequestMethod.GET)
  public List<Orders> getOrdersByState(@PathVariable("state") int state) {
    return ADMIN_SERVICE.getOrderByState(state); // check enumf
  }

  @RequestMapping(value = "get/staff-by-state/{role}", method = RequestMethod.GET)
  public List<Staff> getStaffByState(@PathVariable("role") StaffRole role) {
    return ADMIN_SERVICE.getStaffByRole(role);
  }

  @RequestMapping(value = "update/request", method = RequestMethod.PATCH)
  public String updateRequest(@RequestParam("aid") String aid, @RequestParam("rid") String rid,
      @RequestBody Map<String, String> data) {
    return ADMIN_SERVICE.updateAssignAppraiser(rid, aid, data.get("date"));
  }

  @RequestMapping(value= "get/order-number/{date}", method = RequestMethod.GET)
  public int getOrderNumber(@PathVariable("date") String date){
    return ADMIN_SERVICE.orderOfDay(date); 
  }

  @RequestMapping(value= "get/order-success-number/{date}", method = RequestMethod.GET)
  public int getSuccessOrderNumber(@PathVariable("date") String date){
    return ADMIN_SERVICE.successOrderOfDay(date); 
  }

  @RequestMapping(value= "get/total-amount-success-order/{date}", method= RequestMethod.GET)
  public long getTotalAmountSuccessOrder(@PathVariable("date") String date){
    return ADMIN_SERVICE.totalAmountSuccess(date); 
  }

  @RequestMapping(value= "get/total-amount-order/{date}", method= RequestMethod.GET)
  public long getTotalAmountOrder(@PathVariable("date") String date){
    return ADMIN_SERVICE.totalAmountOrderOfDay(date); 
  }

  @RequestMapping(value= "get/top-3-brand", method = RequestMethod.GET)
  public List<Map<String, Integer>> getTop3brand(){
    return ADMIN_SERVICE.top3Brand(); 
  }

  @RequestMapping(value = "get/profit/{date}", method = RequestMethod.GET)
  public float getProfit(@PathVariable("date") String date) {
    return ADMIN_SERVICE.getWebProfitByDate(date); 
  }

}
