package com.example.TimeHarmony.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Cart {
    @Id
    private String cart_id;

    @ElementCollection
    @CollectionTable(name = "Watches_In_Cart", joinColumns = @JoinColumn(name = "cart_id"))
    @Column(name = "watch_id")
    private List<String> wids;

    public Cart() {
    }
}
