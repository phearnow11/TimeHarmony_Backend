package com.example.TimeHarmony.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Staff;
import com.example.TimeHarmony.enumf.StaffRole;

import jakarta.transaction.Transactional;

public interface StaffRepository extends JpaRepository<Staff, UUID> {

  @Modifying
  @Transactional
  @Query(value = "insert Staff(member_id) values (:id)", nativeQuery = true)
  void toStaff(@Param("id") UUID id);

  @Query(value = "select st.staff_role from Staff st where member_id = :id")
  StaffRole getRole(@Param("id") UUID id);

  @Modifying
  @Transactional
  @Query(value = "insert [dbo].[Shipping_Order](shipper, order_id) values (:id, :oid)", nativeQuery = true)
  void shippingOrder(@Param("id") UUID id, @Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "delete [dbo].[Shipping_Order] where order_id = :oid", nativeQuery = true)
  void shippedOrder(@Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "update Staff set staff_role = :role where member_id = :id")
  void updateStaffRole(@Param("role") StaffRole role, @Param("id") UUID mid);
}
