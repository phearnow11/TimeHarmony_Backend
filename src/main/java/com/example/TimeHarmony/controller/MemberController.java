package com.example.TimeHarmony.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import com.example.TimeHarmony.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;
    private final byte MEMBER_ACTIVATE = 1;
    private final byte DEFAULT_ACTIVE_STATUS = 1;
    private final byte DEFAULT_INACTIVE_STATUS = 0;

    @RequestMapping(value = "get-member", method = RequestMethod.GET)
    public Optional<Members> getMember(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get-addresses", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "save-user", method = RequestMethod.POST)
    public String saveUser(@RequestBody String data) {
        String res = "Member is created";

        JSONArray jsarrdata = new JSONArray(data);
        JSONObject UserJSdata = jsarrdata.getJSONObject(0);

        Users logInfo = new Users(jsarrdata.getJSONObject(1).optString("username"),
                jsarrdata.getJSONObject(1).optString("password"), MEMBER_ACTIVATE);

        Members member = new MemberBuilder()
                .setUserLogInfo(logInfo)
                .setMemberImage(UserJSdata.optString("image"))
                .setFirstName(UserJSdata.optString("Fname"))
                .setLastName(UserJSdata.optString("Lname"))
                .setActive(DEFAULT_INACTIVE_STATUS)
                .setDefaultAddress(UserJSdata.optString("address"))
                .setPhone(UserJSdata.optString("phone"))
                .setEmail(UserJSdata.optString("email"))
                .setLastLoginDate(Timestamp.valueOf(LocalDateTime.now()))
                .setEmailVerify(MEMBER_SERVICE.autoVerificationCodeGenerate())
                .build();

        if (MEMBER_SERVICE.isExist(logInfo, member.getEmail()))
            res = "Member is already exist";
        else
            MEMBER_SERVICE.saveUser(member, logInfo);

        return res;
    }

    @RequestMapping(value = "verify/google", method = RequestMethod.GET)
    public String updateEmailVerificationCode(@RequestParam("member_id") String member_id) {
        String code = MEMBER_SERVICE.updateEmailCode(member_id);
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();
        if (user.getEmail().isEmpty())
            return "User does not have an email";
        // send to email
        return code;
    }

    @RequestMapping(value = "verify/password", method = RequestMethod.GET)
    public String generateForgotPasswordCode(@RequestParam("member_id") String member_id) {
        String code = MEMBER_SERVICE.autoVerificationCodeGenerate();
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();
        if (user.getEmail().isEmpty())
            return "User does not have an email";
        // send to email
        return code;
    }

    @RequestMapping(value = "update/email", method = RequestMethod.PUT)
    public String updateEmail(@RequestParam("member_id") String member_id,
            @RequestParam("email") String new_email) {

        return MEMBER_SERVICE.updateEmail(member_id, new_email);
    }
}
