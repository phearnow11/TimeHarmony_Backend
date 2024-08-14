package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
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

import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Vouchers;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.OrderService;
import com.example.TimeHarmony.service.ReportService;
import com.example.TimeHarmony.service.StaffService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.VoucherService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

  @Autowired
  private StaffService STAFF_SERVICE;
  @Autowired
  private WatchService WATCH_SERVICE;
  @Autowired
  private VoucherService VOUCHER_SERVICE;
  @Autowired
  private StringService STRING_SERVICE;
  @Autowired
  private ReportService REPORT_SERVICE;
  @Autowired
  private OrderService ORDER_SERVICE;

  @RequestMapping(value = "approve-watch", method = RequestMethod.PATCH)
  public String approveWatch(@RequestParam("watch_id") String watch_id) {
    return STAFF_SERVICE.approveWatch(watch_id);
  }

  @RequestMapping(value = "unapprove-watch", method = RequestMethod.PATCH)
  public String unApproveWatch(@RequestParam("watch_id") String watch_id) {
    return STAFF_SERVICE.unApproveWatch(watch_id);
  }

  @RequestMapping(value = "get/watch/{id}", method = RequestMethod.GET)
  public List<Map<String, String>> getWatches(@PathVariable("id") String aid) {
    List<Map<String, String>> res = new ArrayList<>();
    Map<String, String> watch = new Hashtable<>();
    Map<String, List<String>> data = STAFF_SERVICE.getMyAssignedWatch(aid);
    List<Watch> ads = WATCH_SERVICE.getWatchByIds(data.get("wids"));
    for (Watch w : ads) {
      watch.put("id", w.getWatch_id());
      watch.put("name", w.getWatch_name());
      watch.put("seller_id", w.getSeller().getMember_id().toString());
      watch.put("seller_name", w.getSeller().getFirst_name() + w.getSeller().getLast_name());
      watch.put("image", w.getImage_url().isEmpty() ? null : w.getImage_url().get(0));
      watch.put("price", Float.toString(w.getPrice()));
      watch.put("upload_date", w.getWatch_create_date().toString());
      watch.put("status", Byte.toString(w.getState()));
      res.add(new Hashtable<>(watch));
    }
    return res;
  }

  @RequestMapping(value = "get/watch-state/{state}", method = RequestMethod.GET)
  public List<Watch> getWatchesByState0(@PathVariable("state") int state) {
    return STAFF_SERVICE.getAllStateWatch(state);
  }

  @RequestMapping(value = "create/voucher", method = RequestMethod.POST)
  public Vouchers createVoucher(@RequestBody Map<String, Object> data) {

    Vouchers v = new Vouchers(STRING_SERVICE.autoGenerateString(12), data.get("name").toString(),
        data.get("description").toString(), (Integer) data.get("value"),
        Float.parseFloat(data.get("value_percentage").toString()),
        (Integer) data.get("limit"), (Integer) data.get("condition"), Timestamp.valueOf(LocalDateTime.now()),
        Timestamp.valueOf(LocalDateTime.now().plusHours(Long.parseLong(data.get("expired_date").toString()))),
        (Integer) data.get("quantity"));
    return VOUCHER_SERVICE.addVoucher(v);
  }

  @RequestMapping(value = "remove/amount/voucher", method = RequestMethod.PATCH)
  public String removeVoucherAmount(@RequestBody Map<String, Object> data) {
    return VOUCHER_SERVICE.removeVoucherAmount((Integer.parseInt(data.get("num").toString())),
        data.get("vid").toString());
  }

  @RequestMapping(value = "send/unapprove-report", method = RequestMethod.POST)
  public Report sendReportUnapproved(@RequestBody Map<String, String> data) {
    return REPORT_SERVICE.createReport(data);
  }

  @RequestMapping(value = "get/voucher/all", method = RequestMethod.GET)
  public List<Vouchers> getVouchers() {
    return VOUCHER_SERVICE.getVouchers();
  }

  @RequestMapping(value = "remove/perma/voucher", method = RequestMethod.DELETE)
  public String permaDeleteVoucher(@RequestParam("vid") String vid) {
    return VOUCHER_SERVICE.permaDeleteVoucher(vid);
  }

  @RequestMapping(value = "remove/watch/{wid}/{sid}", method = RequestMethod.GET)
  public String removeWatch(@PathVariable("wid") String wid, @PathVariable("sid") String sid) {
    return WATCH_SERVICE.deleteWatch(wid, sid);
  }

  @RequestMapping(value = "get/my-assigned-order/{id}", method = RequestMethod.GET)
  public List<Orders> getMyAssignedOrder(@PathVariable("id") String id) {
    return STAFF_SERVICE.getMyAssignedOrder(id);
  }

  @RequestMapping(value = "ship/order", method = RequestMethod.POST)
  public String shipOrder(@RequestParam("oid") String oid, @RequestParam("id") String id) {
    return STAFF_SERVICE.shipOrder(id, oid);
  }

  @RequestMapping(value = "shipped/order", method = RequestMethod.POST)
  public String shippedOrder(@RequestParam("oid") String oid, @RequestParam("id") String id) {
    return STAFF_SERVICE.shippedOrder(id, oid);
  }

  @RequestMapping(value = "get/my-shipping-order/{id}", method = RequestMethod.GET)
  public List<String> getMyShippingOrder(@PathVariable("id") String id) {
    return STAFF_SERVICE.getMyShippingOrder(id);
  }

  @RequestMapping(value = "save/location/{id}", method = RequestMethod.POST)
  public String saveLocation(@PathVariable("id") String id, @RequestBody Map<String, Object> data) {
    return STAFF_SERVICE.updateLocation(id, data);
  }

  @RequestMapping(value = "update/fields/{watch_id}", method = RequestMethod.PATCH)
  public String updateWatch(@RequestBody Map<String, String> data, @PathVariable String watch_id) {
    Watch existingWatch = WATCH_SERVICE.getWatchById(watch_id);
    existingWatch = WATCH_SERVICE.updateWatch(data, existingWatch);
    if (existingWatch == null)
      return "Update error";
    return "Updated";
  }

  @RequestMapping(value = "get/appraise-requests/from", method = RequestMethod.GET)
  public List<AppraiseRequest> getAllRequests(@RequestParam("sid") String sid) {
    return STAFF_SERVICE.getRequestsFromSeller(sid);
  }

  @RequestMapping(value = "accept/request/{id}", method = RequestMethod.PATCH)
  public String acceptRequest(@PathVariable("id") String aid, @RequestParam("request_id") String request_id) {
    return STAFF_SERVICE.acceptRequest(request_id, aid);
  }

  @RequestMapping(value = "get/my-request/{id}, method = RequestMethod.GET")
  public List<AppraiseRequest> getMyRequest(@PathVariable("id") String aid) {
    return STAFF_SERVICE.getMyRequests(aid);
  }
}
