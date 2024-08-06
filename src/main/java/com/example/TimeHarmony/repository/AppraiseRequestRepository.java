package com.example.TimeHarmony.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.AppraiseRequest;

/**
 * AppraiseRequestRepository
 */

public interface AppraiseRequestRepository extends JpaRepository<AppraiseRequest, String> {

  @Query(value = "select * from [dbo].[Appraise_Request] where created_by = :sid", nativeQuery = true)
  List<AppraiseRequest> getRequestFromSeller(@Param("sid") UUID sid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Appraise_Request] set appraiser_assigned = :aid where request_id = :rid", nativeQuery = true)
  void assignRequest(@Param("aid") UUID aid, @Param("rid") String rid);

  @Query(value = "select request_id from [dbo].[Appraise_Request] where watch_id = :wid", nativeQuery = true)
  List<AppraiseRequest> checkWatch(@Param("wid") String wid);
}
