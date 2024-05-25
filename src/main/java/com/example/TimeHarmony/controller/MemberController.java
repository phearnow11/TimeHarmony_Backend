package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;
import com.example.TimeHarmony.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;

    @RequestMapping(value = "get-member", method = RequestMethod.GET)
    public Optional<Users> getMember(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get-addresses", method = RequestMethod.GET)
    public List<Addresses> getAddresses() {
        return MEMBER_SERVICE.getAddresses();
    }

    @RequestMapping(value = "get-watch", method = RequestMethod.GET)
    public Optional<Watch> getWatch(@RequestParam("gender") String gender) {
        return MEMBER_SERVICE.getWatchByGender(gender);
    }

    @RequestMapping(value = "save-user", method = RequestMethod.POST)
    public Users saveUser(@RequestBody String data) {
        JSONObject jsdata = new JSONObject(data);
        Users user = new MemberBuilder()
                .setUsername(jsdata.optString("username"))
                .setPassword(jsdata.optString("password"))
                .setMemberImage(jsdata.optString("image"))
                .setFirstName(jsdata.optString("Fname"))
                .setLastName(jsdata.optString("Lname"))
                .setActive(1)
                .setDefaultAddress(jsdata.optString("address"))
                .setPhone(jsdata.optString("phone"))
                .setEmail(jsdata.optString("email"))
                .setRole(1)
                .build();
        return MEMBER_SERVICE.saveUser(user);
    }
}
