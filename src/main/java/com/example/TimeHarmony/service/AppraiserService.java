package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IAppraiserService;

public class AppraiserService implements IAppraiserService{
    @Autowired
    private WatchRepository WATCH_REPOSITORY;

   

    @Override
    public void approveWatch(String watch_id) {
        Watch w = WATCH_REPOSITORY.findWatchById(watch_id); 
        w.setWatch_approval_date(Timestamp.valueOf(LocalDateTime.now()));
        w.setState((byte)1); 
    }



    @Override
    public Watch updateWatchProperties(String watch_id, Map<String, Object> data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateWatchProperties'");
    }

}
