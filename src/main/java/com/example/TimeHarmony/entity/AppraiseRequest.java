package com.example.TimeHarmony.entity;

import java.sql.Timestamp;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * AppraiseRequest
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Appraise_Request")
public class AppraiseRequest {

  @Id
  private String request_id;
  private UUID created_by;
  private UUID appraiser_assigned;
  private String appraise_watch;

  @Temporal(TemporalType.TIMESTAMP)
  private Timestamp appointment_date;
  private String note;

  public AppraiseRequest() {
  }
}
