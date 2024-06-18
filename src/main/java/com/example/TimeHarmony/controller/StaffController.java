package com.example.TimeHarmony.controller;

import java.util.ArrayList;
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
    public List<Map<String, String>> getWatches() {
        List<Map<String, String>> res = new ArrayList<>();
        Map<String, String> watch = new HashMap<>();
        List<Watch> ads = WATCH_SERVICE.getWatchesbyState();
        for (Watch w : ads) {
            watch.put("id", w.getWatch_id());
            watch.put("name", w.getWatch_name());
            watch.put("seller_id", w.getSeller().getMember_id().toString());
            watch.put("seller_name", w.getSeller().getFirst_name() + w.getSeller().getLast_name());
            watch.put("image", w.getImage_url().isEmpty() ? null : w.getImage_url().get(0));
            watch.put("price", Long.toString(w.getPrice()));
            watch.put("upload_date", w.getWatch_create_date().toString());
            watch.put("status", Byte.toString(w.getState()));
            res.add(new HashMap<>(watch));
        }
        return res;
    }
}
