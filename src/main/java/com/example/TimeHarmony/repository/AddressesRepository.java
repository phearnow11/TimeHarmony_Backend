package com.example.TimeHarmony.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.TimeHarmony.entity.Addresses;

public interface AddressesRepository extends JpaRepository<Addresses, String> {
    @Query("select a from Addresses a where a.member_id = ?1 ")
    List<Addresses> getAddressesbyMember(String member_id);
}
