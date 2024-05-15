package com.example.TimeHarmony.service;

import java.util.List;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;

public interface IAdminService {

    public List<Members> getMembers();

    public List<Admins> getAdmins();

}
