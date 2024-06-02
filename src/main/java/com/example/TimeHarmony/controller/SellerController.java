package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.builder.WatchBuilder;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.SellerService;
import com.example.TimeHarmony.service.WatchService;

@RestController
@RequestMapping("/seller")
@CrossOrigin
public class SellerController {

    @Autowired
    private SellerService SELLER_SERVICE;
    @Autowired
    private WatchService WATCH_SERVICE;

    private final byte DEFAULT_STATUS = 0;

    @RequestMapping(value = "create/watch", method = RequestMethod.POST)
    public String createWatch(@RequestBody String data) {
        String res = "Watch is created";

        JSONArray jsarrdata = new JSONArray(data);
        JSONObject UserJSdata = jsarrdata.getJSONObject(0);

        Watch watch = new WatchBuilder()
                .setWatchId(WATCH_SERVICE.generateWatchId())
                .setWatchImage(UserJSdata.optString("watchimage"))
                .setWatchDescription(UserJSdata.optString("watchdescript"))
                .setWatchName(UserJSdata.optString("watchname"))
                .setWatchCreateDate(Timestamp.valueOf(LocalDateTime.now()))
                .setState(DEFAULT_STATUS)
                .setPrice(Integer.parseInt(UserJSdata.optString("price")))
                .setBrand(UserJSdata.optString("brand"))
                .setSeries(UserJSdata.optString("series"))
                .setModel(UserJSdata.optString("model"))
                .setGender(UserJSdata.optString("gender"))
                .setStyleType(UserJSdata.optString("style"))
                .setSubClass(UserJSdata.optString("subclass"))
                .setMadeLabel(UserJSdata.optString("madelabel"))
                .setCalender(UserJSdata.optString("calender"))
                .setFeature(UserJSdata.optString("feature"))
                .setMovement(UserJSdata.optString("movement"))
                .setFunctions(UserJSdata.optString("function"))
                .setEngine(UserJSdata.optString("engine"))
                .setWaterResistant(UserJSdata.optString("waterresistant"))
                .setBandColor(UserJSdata.optString("bandcolor"))
                .setBandType(UserJSdata.optString("bandtype"))
                .setClasp(UserJSdata.optString("clasp"))
                .setBracelet(UserJSdata.optString("bracelet"))
                .setDialType(UserJSdata.optString("dialtype"))
                .setDialColor(UserJSdata.optString("dialcolor"))
                .setCrystal(UserJSdata.optString("crystal"))
                .setSecondMakers(UserJSdata.optString("secondmaker"))
                .setBezel(UserJSdata.optString("bezel"))
                .setBezelMaterial(UserJSdata.optString("bezelmaterial"))
                .setCaseBack(UserJSdata.optString("caseback"))
                .setCaseDimension(UserJSdata.optString("casedimension"))
                .setCaseShape(UserJSdata.optString("caseshape"))
                .build();

        // check watch có tồn tại hay chưa nữa

        SELLER_SERVICE.createWatch(watch);
        return res;
    }
}
