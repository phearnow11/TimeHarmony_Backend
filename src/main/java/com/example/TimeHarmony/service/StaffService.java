package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.OrderState;
import com.example.TimeHarmony.enumf.StaffRole;
import com.example.TimeHarmony.repository.OrderRepository;
import com.example.TimeHarmony.repository.StaffRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IStaffService;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private WatchRepository WATCH_REPOSITORY;
    @Autowired
    private OrderRepository ORDER_REPOSITORY;
    @Autowired
    private StaffRepository STAFF_REPOSITORY;

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
            ORDER_REPOSITORY.updateOrderState(OrderState.SHIPPING.getSTATE_VALUE(), oid);
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

}
