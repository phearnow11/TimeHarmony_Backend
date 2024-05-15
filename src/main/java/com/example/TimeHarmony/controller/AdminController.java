package com.example.TimeHarmony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Admins;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.service.AdminService;

@RestController
@RequestMapping("admin")
@CrossOrigin
public class AdminController {
    @Autowired
    private AdminService ADMIN_SERVICE;

    @RequestMapping(value = "getmembers", method = RequestMethod.GET)
    public List<Members> getaMembers() {
        return ADMIN_SERVICE.getMembers();
    }

    @RequestMapping(value = "getadmins", method = RequestMethod.GET)
    public List<Admins> getAdmins() {
        return ADMIN_SERVICE.getAdmins();
    }
}
