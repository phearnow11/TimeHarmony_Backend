package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "member_id")
public class Sellers extends Members {

    private float rate;
    @OneToMany(mappedBy = "seller")
    private List<Watch> watches;

    public Sellers() {
    }

    public Sellers(UUID member_id, String google_id, Users user_log_info, String member_image, String first_name,
            String last_name, int is_active, String email, String phone, Timestamp last_login_date,
            Timestamp last_logout_date, List<Addresses> addresses, List<Orders> myOrders, Instant created_at,
            String cart_id, List<Watch> watches) {
        super(member_id, google_id, user_log_info, member_image, first_name, last_name, is_active, email, phone,
                last_login_date, last_logout_date, addresses, myOrders, created_at, cart_id);
        this.watches = watches;
    }

}
