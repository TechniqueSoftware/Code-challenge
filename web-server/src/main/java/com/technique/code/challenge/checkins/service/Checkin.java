package com.technique.code.challenge.checkins.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

public class Checkin {

  private final Integer memberId;
  private final DateTime checkinTime;

  public Checkin(@JsonProperty("memberId") final Integer memberId,
      @JsonProperty("checkinTime") final DateTime checkinTime) {
    this.memberId = memberId;
    this.checkinTime = checkinTime;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public DateTime getCheckinTime() {
    return checkinTime;
  }
}
