package com.example.TimeHarmony.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.service.EmailService;

@RestController
@RequestMapping("mail")
@CrossOrigin
public class MailController {

    @Autowired
    private EmailService EMAIL_SERVICE;

    @RequestMapping(value = "send-to/{destination}", method = RequestMethod.POST)
    public String sendSimpleMessage(@PathVariable("destination") String destination,
            @RequestBody Map<String, String> data) {
        try {
            EMAIL_SERVICE.sendSimpleMessage(destination, data.get("subject"), data.get("content"));
            return "Mail sent successfully";
        } catch (Exception e) {
            return e.toString();
        }
    }

    @RequestMapping(value = "send-to/attachment/{destination}", method = RequestMethod.POST)
    public String sendAttachMessage(@PathVariable("destination") String destination,
            @RequestBody Map<String, String> data) {
        try {

            EMAIL_SERVICE.sendMessageWithAttachment(destination, data.get("subject"), data.get("content"),
                    data.get("path_attachment"));
            return "Mail sent successfully";
        } catch (Exception e) {
            return e.toString();
        }
    }
}
