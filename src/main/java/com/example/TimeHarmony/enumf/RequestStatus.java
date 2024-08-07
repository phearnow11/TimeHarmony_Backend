package com.example.TimeHarmony.enumf;

/**
 * RequestStatus
 */
public enum RequestStatus {

  NEW(0),
  PROCESSING(1),
  COMPLETED(2),
  FAILED(3),
  EXPIRED(4);

  private int FIELD_VALUE;

  public int getFIELD_VALUE() {
    return FIELD_VALUE;
  }

  public void setFIELD_VALUE(int fIELD_VALUE) {
    FIELD_VALUE = fIELD_VALUE;
  }

  private RequestStatus(int fIELD_VALUE) {
    FIELD_VALUE = fIELD_VALUE;
  }

}
