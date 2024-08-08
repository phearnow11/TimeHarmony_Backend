package com.example.TimeHarmony.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.TimeHarmony.entity.AppraiseRequest;
import com.example.TimeHarmony.enumf.RequestStatus;

/**
 * AppraiseRequestRepository
 */

public interface AppraiseRequestRepository extends JpaRepository<AppraiseRequest, String> {

  @Query(value = "select * from [dbo].[Appraise_Request] where created_by = :sid", nativeQuery = true)
  List<AppraiseRequest> getRequestFromSeller(@Param("sid") UUID sid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Appraise_Request] set appraiser_assigned = :aid, appointment_date = :date where request_id = :rid", nativeQuery = true)
  void assignRequest(@Param("aid") UUID aid, @Param("rid") String rid, @Param("date") Timestamp date);

  @Query(value = "select request_id from [dbo].[Appraise_Request] where appraise_watch = :wid", nativeQuery = true)
  String checkWatch(@Param("wid") String wid);

  @Query(value = "select appraise_watch from [dbo].[Appraise_Request] where request_id = :rid", nativeQuery = true)
  String getWatch(@Param("rid") String request_id);

  @Query(value = "select r.appraise_watch from AppraiseRequest r where appraiser_assigned = :aid")
  List<String> getWatchFromAppraiser(@Param("aid") UUID aid);

  @Query(value = "select * from [dbo].[Appraise_Request] where appraiser_assigned = :aid", nativeQuery = true)
  List<AppraiseRequest> getMyAssignedRequest(@Param("aid") UUID aid);

  @Query(value = "select appraiser_assigned from [dbo].[Appraise_Request] where request_id = :rid", nativeQuery = true)
  String checkAppraiser(@Param("rid") String rid);

  @Query(value = "select r.status from AppraiseRequest r where request_id = :rid")
  RequestStatus getStatus(@Param("rid") String rid);

  @Modifying
  @Transactional
  @Query(value = "update [dbo].[Appraise_Request] set status = 4 where appointment_date < getdate()", nativeQuery = true)
  void updateExpired();

  @Modifying
  @Transactional
  @Query(value = "update AppraiseRequest set status = :status where request_id = :rid")
  void updateStatus(@Param("status") RequestStatus status, @Param("rid") String request_id);

}
