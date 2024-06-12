package com.example.TimeHarmony.dtos;

import java.sql.Timestamp;

import com.example.TimeHarmony.enumf.MessageStatus;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Message {

    private String senderName;
    private String receiverName;
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp date;

    private MessageStatus status;

    public Message() {
    }
}
