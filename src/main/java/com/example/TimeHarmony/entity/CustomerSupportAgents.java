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
@Table(name = "Customer_Support_Agents")
@PrimaryKeyJoinColumn(name = "member_id")
public class CustomerSupportAgents extends Members {

    private String report_id;

}
