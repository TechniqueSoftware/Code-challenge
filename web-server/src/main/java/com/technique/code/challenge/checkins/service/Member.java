package com.technique.code.challenge.checkins.service;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

  private final Integer memberId;
  private final String agreementType;

  public Member(@JsonProperty("memberId") final Integer memberId,
      @JsonProperty("agreementType") final String agreementType) {
    this.memberId = memberId;
    this.agreementType = agreementType;
  }

  public Integer getMemberId() {
    return memberId;
  }

  public String getAgreementType() {
    return agreementType;
  }
}
