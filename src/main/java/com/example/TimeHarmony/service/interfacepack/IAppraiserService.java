package com.example.TimeHarmony.service.interfacepack;

import java.util.Map;

import com.example.TimeHarmony.entity.Watch;

public interface IAppraiserService {

    public Watch updateWatchProperties(String watch_id, Map<String, Object> data);

    public int approveWatch(String watch_id);
}
