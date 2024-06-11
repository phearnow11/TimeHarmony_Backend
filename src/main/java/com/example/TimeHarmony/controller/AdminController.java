package com.example.TimeHarmony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.enumf.Roles;
import com.example.TimeHarmony.service.AdminService;
import com.example.TimeHarmony.service.MemberService;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService ADMIN_SERVICE;
    @Autowired
    private MemberService MEMBER_SERVICE;

    @RequestMapping(value = "get/members", method = RequestMethod.GET)
    public List<Members> getaMembers() {
        return ADMIN_SERVICE.getMembers();
    }

    @RequestMapping(value = "get/admins", method = RequestMethod.GET)
    public List<Admins> getAdmins() {
        return ADMIN_SERVICE.getAdmins();
    }

    @RequestMapping(value = "get/watches", method = RequestMethod.GET)
    public List<Watch> getWatches() {
        return ADMIN_SERVICE.getWatches();
    }

    @RequestMapping(value = "role/upgrade/staff/{id}", method = RequestMethod.POST)
    public String toStaff(@PathVariable("id") String id) {
        Members m = MEMBER_SERVICE.getMemberbyID(id).get();
        if (!m.getUser_log_info().getAuthorities().getAuthority().matches(Roles.ROLE_USER.name()))
            return "Only member can be upgrade!";
        return ADMIN_SERVICE.toStaff(id, m.getUser_log_info().getUsername());
    }
}
