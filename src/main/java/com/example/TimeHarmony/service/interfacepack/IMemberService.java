package com.example.TimeHarmony.service.interfacepack;

import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.entity.Watch;

public interface IMemberService {

    public Optional<Members> getMemberbyID(String member_id);

    public List<Addresses> getAddresses(String member_id);

    public Optional<Watch> getWatchByGender(String gender);

    public Optional<Watch> getWatchBySeries(String series);

    public Optional<Watch> getWatchByBrand(String brand);

    public Optional<Watch> getWatchByStyle(String style);

    public boolean isExist(Users user);

    public Members saveUser(Members user, Users loginInfo);

}
