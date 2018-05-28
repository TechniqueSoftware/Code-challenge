package com.technique.code.challenge.checkins.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class CheckinService {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final Map<Integer, Map<LocalDate, List<Checkin>>> checkinMap;

  @Inject
  CheckinService(final RandomCheckinDataLoader randomCheckinDataLoader,
      final FileCheckinDataLoader fileCheckinDataLoader) {
    final Map<Integer, Map<LocalDate, List<Checkin>>> checkinMapFromFile = fileCheckinDataLoader
        .buildInMemoryCheckinMap();
    if (checkinMapFromFile == null) {
      logger.info("Checkins randomly generated");
      this.checkinMap = randomCheckinDataLoader.buildInMemoryCheckinMap();
    } else {
      logger.info("Checkins loaded from file");
      this.checkinMap = checkinMapFromFile;
    }
  }

  public List<Checkin> getCheckins(final Integer locationId, final LocalDate checkinDate) {
    return getCheckinsFromMap(locationId, checkinDate, checkinMap);
  }

  private List<Checkin> getCheckinsFromMap(final Integer locationId, final LocalDate checkinDate,
      final Map<Integer, Map<LocalDate, List<Checkin>>> fileCheckinMap) {
    if (fileCheckinMap.containsKey(locationId) && fileCheckinMap.get(locationId)
        .containsKey(checkinDate)) {
      return fileCheckinMap.get(locationId).get(checkinDate);
    }
    return Collections.emptyList();
  }
}
