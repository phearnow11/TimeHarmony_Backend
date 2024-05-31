package com.example.TimeHarmony.service.interfacepack;

import com.example.TimeHarmony.entity.Watch;

public interface IAppraiserService {

    public Watch updateWatchProperties(String watch_id);

    public int approveWatch(String watch_id);
}
