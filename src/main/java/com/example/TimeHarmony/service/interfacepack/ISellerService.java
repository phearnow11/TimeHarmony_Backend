package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;

public interface ISellerService {

    public Sellers saveSeller(Sellers seller, Users logInfo);

    public String createWatch(Watch watch, Sellers seller);

    public String deleteWatchById(String watch_id);

    public Watch updateWatchByFields(Map<String, String> data, Watch existingWatch);

    public Watch updateWatch(Watch newWatch, Watch existingWatch);

    public List<Watch> findAllWatchBySeller(String sid);

    public Sellers getSellerbyId(String id);
}
