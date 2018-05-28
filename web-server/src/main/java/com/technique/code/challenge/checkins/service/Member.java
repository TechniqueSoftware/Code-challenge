package com.technique.code.challenge.checkins.service;

public class Member {
  private final Integer memberId;
  private final String agreementType;

  public Member(final Integer memberId, final String agreementType) {
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
