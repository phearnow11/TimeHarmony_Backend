package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;
    private final byte ACTIVATE = 1;

    @RequestMapping(value = "get-member", method = RequestMethod.GET)
    public Optional<Members> getMember(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get-addresses", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "get-watch", method = RequestMethod.GET)
    public Optional<Watch> getWatch(@RequestParam("gender") String gender) {
        return MEMBER_SERVICE.getWatchByGender(gender);
    }

    @RequestMapping(value = "save-user", method = RequestMethod.POST)
    public String saveUser(@RequestBody String data) {
        String res = "Member is created";

        JSONArray jsarrdata = new JSONArray(data);
        JSONObject UserJSdata = jsarrdata.getJSONObject(0);

        Members member = new MemberBuilder()
                .setUsername(jsarrdata.getJSONObject(1).optString("username"))
                .setMemberImage(UserJSdata.optString("image"))
                .setFirstName(UserJSdata.optString("Fname"))
                .setLastName(UserJSdata.optString("Lname"))
                .setActive(1)
                .setDefaultAddress(UserJSdata.optString("address"))
                .setPhone(UserJSdata.optString("phone"))
                .setEmail(UserJSdata.optString("email"))
                .build();

        Users logInfo = new Users(jsarrdata.getJSONObject(1).optString("username"),
                jsarrdata.getJSONObject(1).optString("password"), ACTIVATE);

        if (MEMBER_SERVICE.isExist(logInfo))
            res = "Member is already exist";
        else
            MEMBER_SERVICE.saveUser(member, logInfo);

        return res;
    }
}
