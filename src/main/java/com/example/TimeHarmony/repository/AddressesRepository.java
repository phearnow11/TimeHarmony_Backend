package com.example.TimeHarmony.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TimeHarmony.entity.Addresses;

public interface AddressesRepository extends JpaRepository<Addresses, String> {

}
