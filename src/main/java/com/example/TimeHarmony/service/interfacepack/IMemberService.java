package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface IMemberService {

    public Optional<Members> getMemberbyID(String member_id);

    public List<Addresses> getAddresses(String member_id);

    public boolean isExist(Users user);

    public Members saveUser(Members user, Users loginInfo);

    public Users getUserbyUsername(String username);

    public Members getMemberbyUserLogInfo(Users username);

}
