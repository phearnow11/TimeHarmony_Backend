package com.example.TimeHarmony.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TimeHarmony.entity.Chat;
import com.example.TimeHarmony.repository.ChatRepository;
import com.example.TimeHarmony.service.interfacepack.IChatService;

@Service
public class ChatService implements IChatService {

    @Autowired
    private ChatRepository CHAT_REPOSITORY;

    @Autowired
    private StringService STRING_SERVICE;

    @Override
    public List<Chat> getAllChats() {
        return CHAT_REPOSITORY.findAll();
    }

    @Override
    public List<Chat> getMyChat(String mid) {
        return CHAT_REPOSITORY.getMyChat(UUID.fromString(mid));
    }

    @Override
    public String addToChat(String cur_mid, String with_mid) {
        try {
            if (!CHAT_REPOSITORY.getSpecificChat(UUID.fromString(cur_mid), UUID.fromString(with_mid)).isEmpty())
                throw new Exception("can not create more chat");
            Chat new_chat = new Chat(STRING_SERVICE.autoGenerateString(12), UUID.fromString(cur_mid),
                    UUID.fromString(with_mid),
                    Timestamp.valueOf(LocalDateTime.now()));
            CHAT_REPOSITORY.save(new_chat);
            return "New chat created";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @Override
    public void removeChat(String cur_mid, String with_mid) {
        CHAT_REPOSITORY.deleteChat(UUID.fromString(cur_mid), UUID.fromString(with_mid));
    }

    @Override
    public boolean checkChat(String cur_mid, String with_mid) {
        return CHAT_REPOSITORY.getSpecificChat(UUID.fromString(cur_mid), UUID.fromString(with_mid)).isEmpty();
    }

}
