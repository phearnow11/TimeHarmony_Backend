package com.example.TimeHarmony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService ADMIN_SERVICE;

    @RequestMapping(value = "get-members", method = RequestMethod.GET)
    public List<Users> getaMembers() {
        return ADMIN_SERVICE.getMembers();
    }

    @RequestMapping(value = "get-admins", method = RequestMethod.GET)
    public List<Admins> getAdmins() {
        return ADMIN_SERVICE.getAdmins();
    }

    @RequestMapping(value = "get-watches", method = RequestMethod.GET)
    public List<Watch> getWatches() {
        return ADMIN_SERVICE.getWatches();
    }
}
