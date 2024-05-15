package com.example.TimeHarmony.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.service.MemberService;

@RestController
@RequestMapping("/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService MEMBER_SERVICE;

    @RequestMapping(value = "get-member", method = RequestMethod.GET)
    public Optional<Members> getMember(@RequestParam("member_id") String member_id) {
        return MEMBER_SERVICE.getMemberbyID(member_id);
    }
}
