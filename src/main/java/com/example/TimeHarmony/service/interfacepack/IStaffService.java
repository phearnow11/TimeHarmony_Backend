package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.entity.Watch;

public interface IStaffService {
  public String approveWatch(String watch_id);

  public String unApproveWatch(String watch_id);

  public List<Watch> getAllStateWatch(int state);

  String shipOrder(String id, String oid);

  List<String> getMyShippingOrder(String id);

  String shippedOrder(String id, String oid);

  String updateLocation(String id, Map<String, Object> data);

  List<AppraiseRequest> getRequestsFromSeller(String sid);

  String acceptRequest(String request_id, String aid);
}
