package com.example.TimeHarmony.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.SellerRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.ISellerService;

public class SellerService implements ISellerService {

    @Autowired
    private WatchRepository WATCH_REPOSITORY; 
    @Autowired
    private MemberRepository MEMBER_REPOSITORY; 
    @Autowired
    private SellerRepository SELLER_REPOSITORY; 


    @Override
    public Watch createWatch(Watch watch) {
        return WATCH_REPOSITORY.save(watch); 
    }

    @Override
    public String deleteWatchById(String watch_id) {

        return watch_id + " Deleted";
    }

    @Override
    public Watch updateWatch(Watch watch) {
        // update bên query đi
        return watch;
    }

    @Override
    public Sellers saveSeller(String member_id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveSeller'");
    }

}
