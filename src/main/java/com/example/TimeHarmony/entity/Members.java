package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Members")
@ToString
public class Members {
    @Id
    @GeneratedValue
    private UUID member_id;
    private String google_id;

    @OneToOne
    @JoinColumn(name = "username")
    @JsonIgnoreProperties(value = { "password", "enabled", "authorities", "handler",
            "hibernateLazyInitializer" }, allowSetters = true)
    private Users user_log_info;

    private String member_image;
    private String first_name;
    private String last_name;
    private int is_active;
    private String email;
    private String phone;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp last_login_date;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp last_logout_date;

    @OneToMany(mappedBy = "member")
    private List<Addresses> addresses;

    @OneToMany(mappedBy = "member_created")
    List<Orders> myOrders;

    private String cart_id;

    public Members() {
    }

}
