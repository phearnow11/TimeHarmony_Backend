package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.OrderLocation;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.OrderState;
import com.example.TimeHarmony.enumf.StaffRole;
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

    @Override
    public String approveWatch(String watch_id) {
        byte APPROVED_STATE = 1;
        try {
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
            ORDER_REPOSITORY.shippedOrder(oid);
            STAFF_REPOSITORY.shippedOrder(oid);
            WATCH_REPOSITORY.shippedOrder(oid);
            return "Order shipped confirm";
        } catch (Exception e) {
            return e.toString();
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

            String lid = "L" + STRING_SERVICE.autoGenerateString(11);
            Double latitude = (Double) data.get("latitude");
            Double longtitude = (Double) data.get("longtitude");

            ORDER_LOCATION_REPOSITORY.save(new OrderLocation(lid, oid, latitude, longtitude,
                    UUID.fromString(id), Timestamp.valueOf(LocalDateTime.now()),
                    data.get("note") == null ? null : data.get("note").toString()));

            return "Location updated at " + LocalDateTime.now();
        } catch (Exception e) {
            return e.toString();
        }
    }

}
