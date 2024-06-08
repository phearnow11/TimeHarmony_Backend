package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String createWatch(@RequestBody Map<String, String> data, @RequestParam("seller_id") String id) {
        String res = "Watch is created";

        Watch watch = new WatchBuilder()
                .setWatchId(WATCH_SERVICE.generateWatchId())
                .setWatchImage(data.get("image"))
                .setWatchDescription(data.get("description"))
                .setWatchName(data.get("name"))
                .setWatchCreateDate(Timestamp.valueOf(LocalDateTime.now()))
                .setState(DEFAULT_STATUS)
                .setPrice(Float.parseFloat(data.get("price")))
                .setBrand(data.get("brand"))
                .setSeries(data.get("series"))
                .setModel(data.get("model"))
                .setGender(data.get("gender"))
                .setStyleType(data.get("style"))
                .setSubClass(data.get("subclass"))
                .setMadeLabel(data.get("madelabel"))
                .setCalender(data.get("calender"))
                .setFeature(data.get("feature"))
                .setMovement(data.get("movement"))
                .setFunctions(data.get("function"))
                .setEngine(data.get("engine"))
                .setWaterResistant(data.get("waterresistant"))
                .setBandColor(data.get("bandcolor"))
                .setBandType(data.get("bandtype"))
                .setClasp(data.get("clasp"))
                .setBracelet(data.get("bracelet"))
                .setDialType(data.get("dialtype"))
                .setDialColor(data.get("dialcolor"))
                .setCrystal(data.get("crystal"))
                .setSecondMakers(data.get("secondmaker"))
                .setBezel(data.get("bezel"))
                .setBezelMaterial(data.get("bezelmaterial"))
                .setCaseBack(data.get("caseback"))
                .setCaseDimension(data.get("casedimension"))
                .setCaseShape(data.get("caseshape"))
                .build();

        SELLER_SERVICE.createWatch(watch, id);
        return res;
    }

    @RequestMapping(value ="updateAll/watch", method = RequestMethod.PUT)
    public String updateAllfield(@RequestBody Watch newWatch , @RequestParam("watch_id") String id){
        String res = "Watch is updated";
        return res ; 
    }

    @RequestMapping(value= "updatefields/watch", method = RequestMethod.PATCH)
    public String updateWatch(@RequestBody Map<String,Object> data, @RequestParam("watch_id") String id){
        String res = "Watch is updated";
        
        Watch watch = SELLER_SERVICE.updateWatchByFields(data, id);
        if (watch!= null) {
            return res ; 
        }
        return "Fail to update!"; 
    }
}
