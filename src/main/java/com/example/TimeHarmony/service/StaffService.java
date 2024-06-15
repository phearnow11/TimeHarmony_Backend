package com.example.TimeHarmony.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IStaffService;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public String approveWatch(String watch_id) {
        byte APPROVED_STATE = 1;
        try {
            WATCH_REPOSITORY.updateWatchState((byte) APPROVED_STATE, watch_id);
            return "Watch Approved";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public String createVoucher() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVoucer'");
    }

}
