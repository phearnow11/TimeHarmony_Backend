package com.example.TimeHarmony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.service.StaffService;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService STAFF_SERVICE;

    @RequestMapping(value = "approve-watch", method = RequestMethod.PATCH)
    public String approveWatch(@RequestParam("watch_id") String watch_id) {
        return STAFF_SERVICE.approveWatch(watch_id);
    }
}
