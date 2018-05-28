package com.technique.code.challenge.checkins.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class MemberService {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final Map<Integer, List<Member>> memberMap;
  @Inject
  MemberService(final RandomMemberDataLoader randomMemberDataLoader,
      final FileMemberDataLoader fileMemberDataLoader) {
    final Map<Integer, List<Member>> memberMapFromFile =
        fileMemberDataLoader.buildInMemoryMemberMap();
    if (memberMapFromFile == null) {
      logger.info("Members randomly generated");
      this.memberMap = randomMemberDataLoader.buildInMemoryMemberMap();
    } else {
      logger.info("Members loaded from file");
      this.memberMap = memberMapFromFile;
    }
  }

  public List<Member> getMembers(final Integer locationId) {
    if (memberMap.containsKey(locationId)) {
      return memberMap.get(locationId);
    }
    return Collections.emptyList();
  }
}
