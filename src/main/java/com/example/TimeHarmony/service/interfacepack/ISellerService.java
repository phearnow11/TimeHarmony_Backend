package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;

public interface ISellerService {

    public Sellers saveSeller(Sellers seller, Users logInfo);

    public Watch createWatch(Watch watch, String seller_id);

    public String deleteWatchById(String watch_id);

    public Watch updateWatch(Watch watch);

    public List<Watch> findAllWatchBySeller(Sellers s);
}
