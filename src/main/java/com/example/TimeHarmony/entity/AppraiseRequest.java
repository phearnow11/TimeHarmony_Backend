package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.TimeHarmony.enumf.RequestStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * AppraiseRequest
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Appraise_Request")
public class AppraiseRequest {

  @Id
  private String request_id;
  private UUID created_by;
  private UUID appraiser_assigned;
  private String appraise_watch;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp appointment_date;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp created_at;

  private String note;
  private RequestStatus status;

  public AppraiseRequest() {
  }
}
