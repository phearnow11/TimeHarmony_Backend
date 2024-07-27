package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Watch;

public interface IStaffService {
    public String approveWatch(String watch_id);

    public String unApproveWatch(String watch_id);

    public List<Watch> getAllStateWatch(int state);

    String shipOrder(String id, String oid);

    List<String> getMyShippingOrder(String id);
}
