package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IStaffService;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Autowired
    private VoucherService VOUCHER_SERVICE;

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

}
