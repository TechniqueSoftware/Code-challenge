package com.technique.code.challenge.checkins.service;

import static com.technique.code.challenge.checkins.service.Constants.NUMBER_OF_LOCATIONS;
import static com.technique.code.challenge.checkins.service.Constants.NUMBER_OF_MEMBERS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomMemberDataLoader implements MemberDataLoader {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final List<String> AGREEMENT_TYPES = Arrays.asList("BASIC", "PREMIUM", "PLATINUM", "VIP");

  @Override
  public Map<Integer, List<Member>> buildInMemoryMemberMap() {
    final Map<Integer, List<Member>> randomMap = new HashMap<>();
    for (Integer i = 0; i < NUMBER_OF_LOCATIONS; i++) {
      randomMap.put(i, generateRandomMembers());
    }
    storeJSON(randomMap);
    return randomMap;
  }

  private List<Member> generateRandomMembers() {
    final List<Member> randomMembers = new ArrayList<>();
    for (Integer i = 1; i <= NUMBER_OF_MEMBERS; i++) {
      randomMembers.add(generateRandomMember(i));
    }
    return randomMembers;
  }

  private Member generateRandomMember(final Integer memberId) {
    final Random rand = new Random();
    int index = rand.nextInt(AGREEMENT_TYPES.size());
    return new Member(memberId, AGREEMENT_TYPES.get(index));
  }

  private void storeJSON(final Map<Integer, List<Member>> randomMembersMap) {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JodaModule());
    try {
      final String s = objectMapper.writeValueAsString(randomMembersMap);
      final File file = new File("/tmp/memberData.txt");
      Files.append(s + "\n", file, Charsets.UTF_8);
    } catch (IOException e) {
      logger.error("Error: ", e);
    }
  }
}
