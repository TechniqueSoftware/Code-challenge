package com.technique.code.challenge.checkins.service;

import java.util.List;
import java.util.Map;

public interface MemberDataLoader {

  Map<Integer, List<Member>> buildInMemoryMemberMap();
}
