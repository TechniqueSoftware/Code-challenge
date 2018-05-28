package com.technique.code.challenge.checkins.service;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MemberService {

  @Inject
  MemberService() {
  }

  public List<Member> getMembers(final Integer locationId) {
    return Arrays.asList(new Member(1, "hello world"));
  }
}
