package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
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
    public boolean deleteWatch(String id) {
        if (id != null) {
            Optional<Watch> watch = WATCH_REPOSITORY.findById(id);
            if (watch.isPresent()) {
                WATCH_REPOSITORY.delete(watch.get());
                return true;
            }
        }
        return false;
    }

}
