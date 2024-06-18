package com.example.TimeHarmony.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.StaffService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/staff")
@CrossOrigin
public class StaffController {

    @Autowired
    private StaffService STAFF_SERVICE;
    @Autowired
    private WatchService WATCH_SERVICE;

    @RequestMapping(value = "approve-watch", method = RequestMethod.PATCH)
    public String approveWatch(@RequestParam("watch_id") String watch_id) {
        return STAFF_SERVICE.approveWatch(watch_id);
    }

    @RequestMapping(value = "get/watch", method = RequestMethod.GET)
    public Map<String, String> getWatches() {
        Map<String, String> res = new HashMap<>();
        List<Watch> ads = WATCH_SERVICE.getWatchesbyState();
        return res;
    }
}
