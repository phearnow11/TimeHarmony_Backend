package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.repository.AdminRepository;
import com.example.TimeHarmony.repository.MemberRepository;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Autowired
    private AdminRepository ADMIN_REPOSITORY;

    @Override
    public List<Members> getMembers() {
        return MEMBER_REPOSITORY.findAll();
    }

    public List<Admins> getAdmins() {
        return ADMIN_REPOSITORY.findAll();
    }

}
