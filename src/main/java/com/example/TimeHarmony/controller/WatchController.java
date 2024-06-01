package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/watch")
@CrossOrigin
public class WatchController {

    @Autowired
    private WatchService WATCH_SERVICE;

    @RequestMapping(value = "get/gender", method = RequestMethod.GET)
    public List<Watch> getWatchByGender(@RequestParam("gender") String gender) {
        return WATCH_SERVICE.getWatchByGender(gender);
    }

    @RequestMapping(value = "get/series", method = RequestMethod.GET)
    public List<Watch> getWatchBySeries(@RequestParam("series") String series) {
        return WATCH_SERVICE.getWatchBySeries(series);
    }

    @RequestMapping(value = "get/brand", method = RequestMethod.GET)
    public List<Watch> getWatchByBrand(@RequestParam("brand") String brand) {
        return WATCH_SERVICE.getWatchByBrand(brand);
    }

    @RequestMapping(value = "get/style", method = RequestMethod.GET)
    public List<Watch> getWatchByStyle(@RequestParam("style") String style) {
        return WATCH_SERVICE.getWatchByStyle(style);
    }

    @RequestMapping(value = "filter", method = RequestMethod.GET)
    public Map<String, String> filterChain(@RequestParam Map<String, String> data) {
        return data;
    }

    @RequestMapping(value = "get/30-watches", method = RequestMethod.GET)
    public List<Watch> get30watchesbyDESCdate() {
        return WATCH_SERVICE.get30WatchesSortedByCreationDate();
    }
}
