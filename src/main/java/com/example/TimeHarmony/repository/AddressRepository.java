package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.Addresses;

import jakarta.transaction.Transactional;

public interface AddressRepository extends JpaRepository<Addresses, String> {
    @Modifying
    @Transactional
    @Query(value = "insert Addresses([address_id], [member_id], [name], [phone], [address_detail], [is_default]) values (:id , :m_id , :name , :phone , :detail, :default )", nativeQuery = true)
    void insertAddress(@Param("id") String id, @Param("m_id") UUID m_id, @Param("name") String name,
            @Param("detail") String detail, @Param("default") boolean is_default);

    @Modifying
    @Transactional
    @Query(value = "delete Addresses where member_id = :m_id and address_id = :a_id", nativeQuery = true)
    void deleteAddress(@Param("m_id") UUID m_id, @Param("a_id") String a_id);

    @Query(value = "select * from Addresses where member_id = :mid", nativeQuery = true)
    List<Addresses> getAddresses(@Param("mid") UUID mid);

    @Query(value = "select * from Addresses where member_id = :mid and is_default = :d", nativeQuery = true)
    Optional<Addresses> checkDefault(@Param("mid") UUID mid, @Param("d") Boolean d);

    @Modifying
    @Transactional
    @Query(value = "update Addresses set is_default = :d where address_id = :id", nativeQuery = true)
    void updateDefault(@Param("d") Boolean d, @Param("id") String id);
}
