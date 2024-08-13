package com.example.TimeHarmony.entity;

import java.sql.Timestamp;

import com.example.TimeHarmony.enumf.OrderState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Orders {
    @Id
    private String order_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "addresses", "handler", "hibernateLazyInitializer",
            "watches", "google_id", "user_log_info", "member_image", "first_name", "last_name",
            "is_active", "email", "phone", "last_login_date", "last_logout_date", "myOrders" }, allowSetters = true)
    @JoinColumn(name = "member_id")
    private Members member_created;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp create_time;

    private String address;
    private String receive_name;
    private String phone;
    private String notice;
    private float total_price;
    

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp shipping_date;
    private OrderState state;
    private String payment_method; 

    public Orders() {
    }

}
