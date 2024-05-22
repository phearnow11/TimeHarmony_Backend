package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.repository.AdminRepository;
import com.example.TimeHarmony.repository.MemberRepository;
import com.example.TimeHarmony.repository.WatchRepository;
import com.example.TimeHarmony.service.interfacepack.IAdminService;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Autowired
    private AdminRepository ADMIN_REPOSITORY;

    @Autowired
    private WatchRepository WATCH_REPOSITORY;

    @Override
    public List<Users> getMembers() {
        return MEMBER_REPOSITORY.findAll();
    }

    public List<Admins> getAdmins() {
        return ADMIN_REPOSITORY.findAll();
    }

    @Override
    public List<Watch> getWatches() {
        return WATCH_REPOSITORY.findAll();
    }

}
