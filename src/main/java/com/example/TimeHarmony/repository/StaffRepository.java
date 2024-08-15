package com.example.TimeHarmony.repository;

import java.util.List;
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
  @Query(value = "insert [dbo].[Shipping_Order](shipper, order_id, status) values (:id, :oid, 0)", nativeQuery = true)
  void assignOrderToShipper(@Param("id") UUID id, @Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "delete [dbo].[Shipping_Order] where order_id = :oid and status = 0", nativeQuery = true)
  void unassignOrderShipper(@Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "select status from [dbo].[Shipping_Order] where order_id = :oid", nativeQuery = true)
  int getAssignOrderShipperStatus(@Param("oid") String oid);

  @Query(value = "select order_id from [dbo].[Shipping_Order] where status = 0 and shipper = :sid", nativeQuery = true)
  List<String> getMyAssignedOrder(@Param("sid") UUID sid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Shipping_Order] set status = 1 where shipper = :id and order_id = :oid", nativeQuery = true)
  void shippingOrder(@Param("id") UUID id, @Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "delete [dbo].[Shipping_Order] where order_id = :oid", nativeQuery = true)
  void shippedOrder(@Param("oid") String oid);

  @Modifying
  @Transactional
  @Query(value = "update Staff set staff_role = :role where member_id = :id")
  void updateStaffRole(@Param("role") StaffRole role, @Param("id") UUID mid);

  @Query(value = "select s from Staff s where s.staff_role = :sr ")
  List<Staff> getStaffByRole(@Param("sr") StaffRole sr);

  @Query(value = "select shipper from [dbo].[Shipping_Order] where order_id = :oid", nativeQuery = true)
  String getShipperAssigned(@Param("oid") String oid);

}
