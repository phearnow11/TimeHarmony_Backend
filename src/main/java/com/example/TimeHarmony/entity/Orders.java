package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
    private long total_price;

    @ElementCollection
    @CollectionTable(name = "Watches_In_Cart", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "watch_id")
    private List<String> wids;

    public Orders() {
    }

}
