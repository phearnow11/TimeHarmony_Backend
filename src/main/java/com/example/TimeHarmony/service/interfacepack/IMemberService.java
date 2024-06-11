package com.example.TimeHarmony.service.interfacepack;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.dtos.AccessHistory;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Report;
import com.example.TimeHarmony.entity.Users;

public interface IMemberService {

    public Optional<Members> getMemberbyID(String member_id);

    public List<Addresses> getAddresses(String member_id);

    public boolean isExist(Users user, String email);

    public Members saveUser(Members user, Users loginInfo);

    public Users getUserbyUsername(String username);

    public Members getMemberbyUserLogInfo(Users username);

    public void login(String member_id);

    public void logout(String member_id);

    public String changeUserPassword(String username, String new_password);

    public String updateEmail(String member_id, String new_email);

    public Report createReport(Report report);

    public List<AccessHistory> getAllAccessHistories(String member_id);

    public Addresses addAddress(Addresses address);

    public String updateAccessHistories(String member_id, List<String> urls, List<Timestamp> times);

    public String toSeller(String m_id, String username);

}
