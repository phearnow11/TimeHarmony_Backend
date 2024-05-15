package com.example.TimeHarmony.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.repository.MemberRepository;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private MemberRepository MEMBER_REPOSITORY;

    @Override
    public Optional<Members> getMemberbyID(String member_id) {
        if (member_id.isEmpty())
            return null;
        Optional<Members> member = Optional.empty();
        member = MEMBER_REPOSITORY.findMemberByID(member_id);
        if (member.isPresent())
            return member;
        return null;
    }

}
