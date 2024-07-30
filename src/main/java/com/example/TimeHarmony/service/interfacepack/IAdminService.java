package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.StaffRole;

public interface IAdminService {

  public List<Members> getMembers();

  public List<Admins> getAdmins();

  public List<Watch> getWatches();

  public String deleteWatch(String id);

  public String deleteMemberbyId(String id);

  public String banMemberbyId(String username);

  public String unbanMemberbyId(String username);

  public List<Map<String, String>> viewWatchCreationHistory();

  public List<Report> viewReports();

  public String toStaff(String id, String username);

  public String addMembers(List<Members> m);

  public String addWatches(List<Watch> w, String s_id) throws Exception;

  public String testUser();

  public float getProfit();

  List<String> getAllShippingOrder();

  String changeStaffRole(String id, StaffRole role);
}
