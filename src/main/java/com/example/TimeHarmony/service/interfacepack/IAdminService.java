package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Map;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Watch;

public interface IAdminService {

    public List<Members> getMembers();

    public List<Admins> getAdmins();

    public List<Watch> getWatches();

    public String deleteWatch(String id);

    public String deleteMemberbyId(String id);

    public String banMemberbyId(String id);

    public String unbanMemberbyId(String id);

    public List<Map<String, String>> viewWatchCreationHistory();

    public List<Report> viewReports();

    public String toStaff(String id, String username);

    public String addMembers(List<Members> m);

    public String addWatches(List<Watch> w, String s_id) throws Exception;

    public String testUser();

}
