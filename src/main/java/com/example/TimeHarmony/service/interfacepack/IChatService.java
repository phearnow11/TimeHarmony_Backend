package com.example.TimeHarmony.service.interfacepack;

import java.util.List;

import com.example.TimeHarmony.entity.Chat;

public interface IChatService {
    List<Chat> getAllChats();

    List<Chat> getMyChat(String mid);

    void addToChat(String cur_mid, String with_mid);

    void removeChat(String cur_mid, String with_mid);
}
