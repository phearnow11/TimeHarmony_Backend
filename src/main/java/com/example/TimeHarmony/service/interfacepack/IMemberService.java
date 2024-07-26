package com.example.TimeHarmony.service.interfacepack;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.example.TimeHarmony.dtos.AccessHistory;
import com.example.TimeHarmony.dtos.Favorites;
import com.example.TimeHarmony.entity.Addresses;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;

public interface IMemberService {

    public Optional<Members> getMemberbyID(String member_id);

    public Members getMemberbyEmail(String email);

    public boolean isExist(Users user, String email);

    public Members saveUser(Members user, Users loginInfo);

    public Users getUserbyUsername(String username);

    public Members getMemberbyUserLogInfo(Users username);

    public void login(String member_id);

    public void logout(String member_id);

    public String changeUserPassword(String username, String new_password);

    public String updateEmail(String member_id, String new_email);

    public String toSeller(String m_id, String username);

    public List<AccessHistory> getAllAccessHistories(String member_id);

    public String updateAccessHistories(String member_id, List<String> urls, List<Timestamp> times);

    public Addresses addAddress(Addresses address);

    public Addresses getDefaultAddress(String m_id);

    public List<Addresses> getAddresses(String member_id);

    public String deleteAddress(String m_id, String a_id);

    public List<Favorites> getFavoritesFromMember(String m_id);

    public String addFavorites(String m_id, String wid);

    public String deleteFavorites(String m_id, String w_id);

    public String deleteMember(String id);

    public String updateMemberImage(String id, String url);

    public Addresses getAddressByAddressId(String addrId);

    public String checkPassword(String username, String rawpassword);

    public Boolean checkUserEnabled(String username);

}
