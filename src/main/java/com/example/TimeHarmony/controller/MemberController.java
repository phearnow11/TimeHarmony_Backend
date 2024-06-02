package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.service.EmailService;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.StringService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Autowired
    private EmailService EMAIL_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    private final String DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE = "Email Verification Code";
    private final String DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD = "Password Changing Verification Code";

    @RequestMapping(value = "get/member", method = RequestMethod.GET)
    public Optional<Members> getMember(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get/addresses", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "verify/google/getcode", method = RequestMethod.GET)
    public String updateEmailVerificationCode(@RequestParam("member_id") String member_id) {
        String code = MEMBER_SERVICE.updateEmailCode(member_id);
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();
        if (user.getEmail().isEmpty())
            return "User does not have an email";
        EMAIL_SERVICE.sendSimpleMessage(user.getEmail(), DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE, code);
        return code;
    }

    @RequestMapping(value = "verify/password/getcode", method = RequestMethod.GET)
    public String generateForgotPasswordCode(@RequestParam("member_id") String member_id) {
        String code = MEMBER_SERVICE.autoVerificationCodeGenerate();
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();
        if (user.getEmail().isEmpty())
            return "User does not have an email";
        EMAIL_SERVICE.sendSimpleMessage(user.getEmail(), DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD, code);
        return code;
    }

    @RequestMapping(value = "update/email", method = RequestMethod.PUT)
    public String updateEmail(@RequestParam("member_id") String member_id,
            @RequestParam("email") String new_email) {

        return MEMBER_SERVICE.updateEmail(member_id, new_email);
    }

    @RequestMapping(value = "update/history", method = RequestMethod.POST)
    public String updateHistories(@RequestBody Map<String, Object> data) {
        System.out.println(data.get("url"));
        System.out.println(STRING_SERVICE.jsonArrToStringList(data.get("url")).get(0));
        return "Hi";
    }
}
