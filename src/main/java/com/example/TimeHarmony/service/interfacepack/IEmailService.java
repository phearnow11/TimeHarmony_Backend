package com.example.TimeHarmony.service.interfacepack;

import jakarta.mail.MessagingException;

public interface IEmailService {

    public void sendSimpleMessage(String to, String subject, String text);

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
            throws MessagingException;

}
