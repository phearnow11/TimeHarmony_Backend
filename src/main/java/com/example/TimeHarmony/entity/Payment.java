package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Payment {
    @Id
    private String transaction_no;
    private String order_id;
    private String member_id; 

    private long payment_amount;
    private long web_profit ; 
    private String bank_code;
    private String payment_method;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp create_at;

    public Payment() {
    }

}
