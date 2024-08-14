package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.OrderLocation;
import com.example.TimeHarmony.entity.Orders;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.OrderState;
import com.example.TimeHarmony.enumf.RequestStatus;
import com.example.TimeHarmony.enumf.StaffRole;
import com.example.TimeHarmony.repository.AppraiseRequestRepository;
import com.example.TimeHarmony.repository.OrderLocationRepository;
import com.example.TimeHarmony.repository.OrderRepository;
import com.example.TimeHarmony.repository.StaffRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IStaffService;

import jakarta.el.ELException;

@Service
public class StaffService implements IStaffService {
  @Autowired
  private WatchRepository WATCH_REPOSITORY;
  @Autowired
  private OrderRepository ORDER_REPOSITORY;
  @Autowired
  private StaffRepository STAFF_REPOSITORY;
  @Autowired
  private OrderLocationRepository ORDER_LOCATION_REPOSITORY;
  @Autowired
  private StringService STRING_SERVICE;
  @Autowired
  private AppraiseRequestRepository APPRAISE_REQUEST_REPOSITORY;
  @Autowired
  private OrderService ORDER_SERVICE;

  public Timer ORDER_SUCCESS_TIMER;

  @Override
  public String approveWatch(String watch_id) {
    byte APPROVED_STATE = 1;
    try {
      if (APPRAISE_REQUEST_REPOSITORY.getStatusViaWatch(watch_id) != RequestStatus.PROCESSING)
        throw new Exception("Logic Error");
      APPRAISE_REQUEST_REPOSITORY.updateStatusViaWatch(RequestStatus.COMPLETED, watch_id);
      WATCH_REPOSITORY.approveWatch(Timestamp.valueOf(LocalDateTime.now()), watch_id, APPROVED_STATE);
      ;
      return "Watch Approved";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Watch> getAllStateWatch(int state) {
    try {
      return WATCH_REPOSITORY.getWatchesByState(state);
    } catch (Exception e) {
      return null;
    }

  }

  @Override
  public String unApproveWatch(String watch_id) {
    byte UNAPPROVED_STATE = 2;
    try {
      if (APPRAISE_REQUEST_REPOSITORY.getStatusViaWatch(watch_id) != RequestStatus.PROCESSING)
        throw new Exception("Logic Error");
      APPRAISE_REQUEST_REPOSITORY.updateStatusViaWatch(RequestStatus.FAILED, watch_id);
      WATCH_REPOSITORY.approveWatch(Timestamp.valueOf(LocalDateTime.now()), watch_id, UNAPPROVED_STATE);
      ;
      return "Watch Deleted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String shipOrder(String id, String oid) {
    try {
      if (STAFF_REPOSITORY.getRole(UUID.fromString(id)) != StaffRole.SHIPPER)
        throw new Exception("You are not shipper");
      if (ORDER_REPOSITORY.getOrderWatchStates(oid).contains(3))
        throw new Exception("Order is not packed");
      if (ORDER_REPOSITORY.getState(oid) == OrderState.SHIPPING)
        throw new Exception("Order is already shipping");
      if (STAFF_REPOSITORY.getShipperAssigned(oid) == null)
        throw new Exception("Order is not assigned");
      if (STAFF_REPOSITORY.getShipperAssigned(oid) != UUID.fromString(id))
        throw new Exception("Order is assigned to someone else");
      ORDER_REPOSITORY.shipOrder(oid);
      STAFF_REPOSITORY.shippingOrder(UUID.fromString(id), oid);
      return "Order is shipping";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<String> getMyShippingOrder(String id) {
    return ORDER_REPOSITORY.getShippingOrderFromShipper(UUID.fromString(id));
  }

  @Override
  public String shippedOrder(String id, String oid) {
    try {
      if (STAFF_REPOSITORY.getRole(UUID.fromString(id)) != StaffRole.SHIPPER)
        throw new Exception("You are not shipper");
      if (ORDER_REPOSITORY.getState(oid) != OrderState.SHIPPING)
        throw new Exception("Logic error");

      // Auto Confirm Order after 3 days
      LocalDateTime fifteenMinuteLater = LocalDateTime.now().plusDays(3);
      Date fifteenMinuteLaterAsDate = Date.from(fifteenMinuteLater.atZone(ZoneId.systemDefault()).toInstant());
      TimerTask orderSuccessTask = new TimerTask() {
        public void run() {
          ORDER_SERVICE.confirmOrder(oid);
        }
      };

      ORDER_SUCCESS_TIMER = new Timer();
      ORDER_SUCCESS_TIMER.schedule(orderSuccessTask, fifteenMinuteLaterAsDate);

      ORDER_REPOSITORY.shippedOrder(oid);
      STAFF_REPOSITORY.shippedOrder(oid);
      WATCH_REPOSITORY.shippedOrder(oid);
      return "Order shipped confirm";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<Orders> getMyAssignedOrder(String sid) {
    try {
      if (STAFF_REPOSITORY.getRole(UUID.fromString(sid)) != StaffRole.SHIPPER)
        throw new Exception("Not Shipper");
      List<String> ids = STAFF_REPOSITORY.getMyAssignedOrder(UUID.fromString(sid));
      return ORDER_REPOSITORY.findAllById(ids);
    } catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
  }

  @Override
  public String updateLocation(String id, Map<String, Object> data) {
    try {

      if (data.get("oid") == null)
        throw new ELException("Missing Order ID");

      String oid = data.get("oid").toString();
      OrderState state = ORDER_REPOSITORY.getState(oid);

      if (state == OrderState.DELETED || state == OrderState.PENDING || state == OrderState.FAILED)
        throw new Exception("Logic Error");

      Optional<OrderLocation> cur_Location = ORDER_LOCATION_REPOSITORY.getLocationFromOrder(oid);
      Double latitude = (Double) data.get("latitude");
      Double longtitude = (Double) data.get("longtitude");

      if (cur_Location.isEmpty()) {
        String lid = "L" + STRING_SERVICE.autoGenerateString(11);
        OrderLocation new_Location = new OrderLocation(lid, oid, latitude, longtitude, UUID.fromString(id),
            Timestamp.valueOf(LocalDateTime.now()), null,
            data.get("note") == null ? null : data.get("note").toString());
        ORDER_LOCATION_REPOSITORY.save(new_Location);
      } else {
        OrderLocation new_Location = cur_Location.get();
        new_Location.setLatitude(latitude);
        new_Location.setLongitude(longtitude);
        new_Location.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
        ORDER_LOCATION_REPOSITORY.save(new_Location);
      }

      return "Location updated at " + LocalDateTime.now();
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<AppraiseRequest> getRequestsFromSeller(String sid) {
    try {
      return APPRAISE_REQUEST_REPOSITORY.getRequestFromSeller(UUID.fromString(sid));
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String acceptRequest(String request_id, String aid) {
    try {
      return "Request Accepted";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<AppraiseRequest> getMyRequests(String aid) {
    try {
      return APPRAISE_REQUEST_REPOSITORY.getMyAssignedRequest(UUID.fromString(aid));
    } catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
  }

  @Override
  public Map<String, List<String>> getMyAssignedWatch(String aid) {
    try {
      Map<String, List<String>> res = new HashMap<>();
      List<String> wids = new ArrayList<>();
      List<String> rids = new ArrayList<>();
      for (AppraiseRequest request : APPRAISE_REQUEST_REPOSITORY.getMyAssignedRequest(UUID.fromString(aid))) {
        wids.add(request.getAppraise_watch());
        rids.add(request.getRequest_id());
      }
      res.put("wids", wids);
      res.put("rids", rids);
      return res;
    } catch (Exception e) {
      System.out.println(e.toString());
      return null;
    }
  }

}
