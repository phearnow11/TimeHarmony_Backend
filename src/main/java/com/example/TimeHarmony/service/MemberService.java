package com.example.TimeHarmony.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.repository.MemberRepository;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

}
