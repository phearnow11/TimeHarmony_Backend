package com.example.TimeHarmony.service.interfacepack;

import com.example.TimeHarmony.entity.Watch;

public interface ISellerService {

    public Watch createWatch(Watch watch);

    public String deleteWatchById(String watch_id);

    public Watch updateWatch(Watch watch);
}
