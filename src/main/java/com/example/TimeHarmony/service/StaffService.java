package com.example.TimeHarmony.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IStaffService;

public class StaffService implements IStaffService {
    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public void approveWatch(String watch_id) {
        Watch w = WATCH_REPOSITORY.findById(watch_id).get();
        w.setState((byte) 1);
    }

    @Override
    public void createVoucher() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVoucer'");
    }

}
