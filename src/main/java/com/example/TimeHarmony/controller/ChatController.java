package com.example.TimeHarmony.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.dtos.Message;
import com.example.TimeHarmony.service.ChatService;

@RestController
@RequestMapping("chat")
@CrossOrigin
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatService CHAT_SERVICE;

    @MessageMapping("/message") // app/message
    @SendTo("/chatroom/public")
    public Message receivePublicMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    public Message receivePrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message); // user/{name}/private
        return message;
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    public List<String> getMyChat(@RequestParam("user_id") String mid) {
        return CHAT_SERVICE.getMyChat(mid).stream().map(c -> c.getWith_member().toString())
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/addtochat", method = RequestMethod.POST)
    public String addChat(@RequestParam("user_id") String mid, @RequestParam("user_id2") String mid2) {
        return CHAT_SERVICE.addToChat(mid, mid2);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleleChat(@RequestParam("user_id") String mid, @RequestParam("user_id2") String mid2) {
        try {
            CHAT_SERVICE.removeChat(mid, mid2);
            return "Success";
        } catch (Exception e) {
            return "Failed due to " + e;
        }
    }
}
