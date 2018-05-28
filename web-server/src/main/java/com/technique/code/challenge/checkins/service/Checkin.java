package com.technique.code.challenge.checkins.service;

import org.joda.time.DateTime;

public class Checkin {
  private final Integer memberId;
  private final DateTime checkinTime;

  public Checkin(final Integer memberId, final DateTime checkinTime) {
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
