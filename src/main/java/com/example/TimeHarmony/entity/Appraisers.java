package com.example.TimeHarmony.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "Appraisers")
@PrimaryKeyJoinColumn(name = "member_id")
public class Appraisers extends Members {

    private String watch_id;
    private String year_experience;

}
