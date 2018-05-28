package com.technique.code.challenge.checkins.service;

import java.util.List;
import java.util.Map;
import org.joda.time.LocalDate;

interface CheckinDataLoader {

  Map<Integer, Map<LocalDate, List<Checkin>>> buildInMemoryCheckinMap();
}
