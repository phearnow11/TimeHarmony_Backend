package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
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
    public List<Members> getMembers() {
        return MEMBER_REPOSITORY.findAll();
    }

    @Override
    public List<Admins> getAdmins() {
        return ADMIN_REPOSITORY.findAll();
    }

    @Override
    public List<Watch> getWatches() {
        return WATCH_REPOSITORY.findAll();
    }

    @Override
    public void deleteWatch(String id) {
        WATCH_REPOSITORY.deleteById(id);
    }

    @Override
    public void deleteMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMemberbyId'");
    }

    @Override
    public void banMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'banMemberbyId'");
    }

    @Override
    public void unbanMemberbyId(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unbanMemberbyId'");
    }

    @Override
    public List<Watch> viewWatchCreationHistory() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewWatchCreateHistory'");
    }

    @Override
    public List<Report> viewReports() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'viewReports'");
    }

}
