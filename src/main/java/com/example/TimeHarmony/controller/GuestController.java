package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Sellers;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.SellerService;

@RestController
@RequestMapping("/guest")
@CrossOrigin
public class GuestController {

    @Autowired
    private MemberService MEMBER_SERVICE;
    @Autowired
    private SellerService SELLER_SERVICE;

    private final byte MEMBER_ACTIVATE = 1;
    private final byte DEFAULT_INACTIVE_STATUS = 0;

    @RequestMapping(value = "register/member", method = RequestMethod.POST)
    public String saveUser(@RequestBody String data) {
        String res = "Member is created";

        JSONArray jsarrdata = new JSONArray(data);
        JSONObject UserJSdata = jsarrdata.getJSONObject(0);

        Users logInfo = new Users(jsarrdata.getJSONObject(1).optString("username"),
                jsarrdata.getJSONObject(1).optString("password"), null, MEMBER_ACTIVATE);

        Members member = new MemberBuilder()
                .setUserLogInfo(logInfo)
                .setMemberImage(UserJSdata.optString("image"))
                .setFirstName(UserJSdata.optString("Fname"))
                .setLastName(UserJSdata.optString("Lname"))
                .setActive(DEFAULT_INACTIVE_STATUS)
                .setPhone(UserJSdata.optString("phone"))
                .setEmail(UserJSdata.optString("email"))
                .build();

        if (MEMBER_SERVICE.isExist(logInfo, member.getEmail()))
            res = "Member is already exist";
        else
            MEMBER_SERVICE.saveUser(member, logInfo);

        return res;
    }

    @RequestMapping(value = "register/seller", method = RequestMethod.POST)
    public String saveSeller(@RequestBody List<Map<String, String>> data) {

        Map<String, String> detail_info = data.get(0);
        Map<String, String> user_info = data.get(1);

        Users logInfo = new Users(user_info.get("username"), user_info.get("password"), null, MEMBER_ACTIVATE);

        Members m = new MemberBuilder()
                .setUserLogInfo(logInfo)
                .setMemberImage(detail_info.get("image"))
                .setFirstName(detail_info.get("Fname"))
                .setLastName(detail_info.get("Lname"))
                .setActive(DEFAULT_INACTIVE_STATUS)
                .setPhone(detail_info.get("phone"))
                .setEmail(detail_info.get("email"))
                .build();

        Sellers s = new Sellers();
        BeanUtils.copyProperties(m, s);

        if (MEMBER_SERVICE.isExist(logInfo, m.getEmail())) {
            return "Seller is already created";
        }
        SELLER_SERVICE.saveSeller(s, logInfo);
        return "Seller is created";
    }

}
