package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public Optional<Members> getMember(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }

    @RequestMapping(value = "get/addresses/{id}", method = RequestMethod.GET)
    public List<Addresses> getAddresses(@PathVariable("id") String member_id) {
        return MEMBER_SERVICE.getAddresses(member_id);
    }

    @RequestMapping(value = "verify/{type}/getcode/{id}", method = RequestMethod.GET)
    public String updateEmailVerificationCode(@PathVariable("id") String member_id, @PathVariable("type") String type) {
        String code = MEMBER_SERVICE.autoVerificationCodeGenerate();
        Members user = MEMBER_SERVICE.getMemberbyID(member_id).get();

        if (user.getEmail().isEmpty())
            return "User does not have an email";

        String subject_mail = "";
        switch (type) {
            case "google":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE;
                break;
            case "password":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD;
                break;
            default:
                break;
        }

        EMAIL_SERVICE.sendSimpleMessage(user.getEmail(), subject_mail, code);
        return code;
    }

    @RequestMapping(value = "update/email/{id}", method = RequestMethod.PUT)
    public String updateEmail(@PathVariable("id") String member_id,
            @RequestParam("email") String new_email) {

        return MEMBER_SERVICE.updateEmail(member_id, new_email);
    }

    @RequestMapping(value = "update/history", method = RequestMethod.POST)
    public String updateHistories(@RequestBody Map<String, Object> data) {
        System.out.println(data.get("url"));
        System.out.println(STRING_SERVICE.jsonArrToStringList(data.get("url")).get(0));
        return "Hi";
    }

    @RequestMapping(value = "save/address/{id}", method = RequestMethod.POST)
    public Addresses saveAddresses(@RequestBody Map<String, String> data, @PathVariable("id") String member_id) {
        Members cur_m = MEMBER_SERVICE.getMemberbyID(member_id).get();
        Addresses nAdd = new Addresses(data.get("address_id"), cur_m, data.get("name"),
                data.get("phone"), data.get("detail"), Boolean.valueOf(data.get("default")));
        return MEMBER_SERVICE.addAddress(nAdd);
    }
}
