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

    public List<Watch> getListWatchDECS(); 

    public List<Watch> getWatchByGender(String gender);

    public List<Watch> getWatchBySeries(String series);

    public List<Watch> getWatchByBrand(String brand);

    public List<Watch> getWatchByStyle(String style);

    public boolean isExist(Users user);

    public Members saveUser(Members user, Users loginInfo);

}
