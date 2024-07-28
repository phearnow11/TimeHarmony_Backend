package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.OrderLocation;

public interface OrderLocationRepository extends JpaRepository<OrderLocation, String> {
    @Query(value = "select * from Order_Location where order_id = :oid", nativeQuery = true)
    List<OrderLocation> getOrderLocations(@Param("oid") String oid);

    @Query(value = "select * from [dbo].[Order_Location] where order_id = :oid", nativeQuery = true)
    Optional<OrderLocation> getLocationFromOrder(@Param("oid") String oid);
}
