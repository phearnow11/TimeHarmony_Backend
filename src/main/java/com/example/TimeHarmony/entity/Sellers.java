package com.example.TimeHarmony.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "member_id")
public class Sellers extends Members {


    @OneToMany(mappedBy = "seller")
    private List<Watch> watches;

   

    
}
