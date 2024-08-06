package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.TimeHarmony.entity.AppraiseRequest;

/**
 * AppraiseRequestRepository
 */

public interface AppraiseRequestRepository extends JpaRepository<AppraiseRequest, String> {

  @Query(value = "select * from [dbo].[Appraise_Request] where created_by = :sid", nativeQuery = true)
  List<AppraiseRequest> getRequestFromSeller(@Param("sid") UUID sid);
}
