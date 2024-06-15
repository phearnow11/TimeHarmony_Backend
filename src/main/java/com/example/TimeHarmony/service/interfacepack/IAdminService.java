package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Watch;

public interface IAdminService {

    public List<Members> getMembers();

    public List<Admins> getAdmins();

    public List<Watch> getWatches();

    public void deleteWatch(String id);

    public void deleteMemberbyId(String id);

    public void banMemberbyId(String id);

    public void unbanMemberbyId(String id);

    public List<Watch> viewWatchCreationHistory();

    public List<Report> viewReports();

    public String toStaff(String id, String username);

    public String addMembers(List<Members> m);

    public String addWatches(List<Watch> w, String s_id) throws Exception;

    public String testUser();

}
