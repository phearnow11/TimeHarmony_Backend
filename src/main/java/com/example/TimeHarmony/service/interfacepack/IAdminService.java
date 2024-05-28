package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Watch;

public interface IAdminService {

    public List<Members> getMembers();

    public List<Admins> getAdmins();

    public List<Watch> getWatches();

    public boolean deleteWatch(String id); 

}
