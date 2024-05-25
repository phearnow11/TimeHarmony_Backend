package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.CustomerSupportAgents;

public interface CustomerSupportAgentsRepository extends JpaRepository<CustomerSupportAgents, UUID> {

}
