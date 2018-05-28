package com.technique.code.challenge.checkins.service;

import static com.technique.code.challenge.checkins.service.Constants.BASE_CHECKIN_DATE;
import static com.technique.code.challenge.checkins.service.Constants.NUMBER_OF_DATES;
import static com.technique.code.challenge.checkins.service.Constants.NUMBER_OF_LOCATIONS;
import static com.technique.code.challenge.checkins.service.Constants.NUMBER_OF_MEMBERS;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.inject.Singleton;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RandomCheckinDataLoader implements CheckinDataLoader {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Map<Integer, Map<LocalDate, List<Checkin>>> buildInMemoryCheckinMap() {
    final Map<Integer, Map<LocalDate, List<Checkin>>> locationMap = new HashMap<>();
    for (int i = 0; i < NUMBER_OF_LOCATIONS; i++) {
      locationMap.put(i + 1, createRandomMapOfCheckins());
    }
    storeJSON(locationMap);
    return locationMap;
  }

  private Map<LocalDate, List<Checkin>> createRandomMapOfCheckins() {
    final Map<LocalDate, List<Checkin>> randomCheckinsMap = new HashMap<>();
    for (int i = 0; i < NUMBER_OF_DATES; i++) {
      randomCheckinsMap.put(BASE_CHECKIN_DATE.plusDays(i), createRandomListOfCheckins(BASE_CHECKIN_DATE.plusDays(i)));
    }
    return randomCheckinsMap;
  }

  private List<Checkin> createRandomListOfCheckins(final LocalDate localDate) {
    final Random rand = new Random();
    int numberOfCheckins = rand.nextInt(200) + 1;
    final List<Checkin> checkins = new ArrayList<>();
    for (int i = 0; i < numberOfCheckins; i++) {
      checkins.add(createRandomCheckin(localDate));
    }
    return checkins;
  }

  private Checkin createRandomCheckin(final LocalDate localDate) {
    final Random rand = new Random();
    int memberId = rand.nextInt(NUMBER_OF_MEMBERS) + 1;
    final Random randHour = new Random();
    int hour = randHour.nextInt(24);
    final Random randMin = new Random();
    int minute = randMin.nextInt(60);

    return new Checkin(memberId,
        localDate.toDateTime(new LocalTime(hour, minute, 0), DateTimeZone.UTC));
  }

  private void storeJSON(final Map<Integer, Map<LocalDate, List<Checkin>>> randomCheckinsMap) {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JodaModule());
    try {
      final String s = objectMapper.writeValueAsString(randomCheckinsMap);
      final File file = new File("/tmp/checkinData.txt");
      Files.append(s + "\n", file, Charsets.UTF_8);
    } catch (IOException e) {
      logger.error("Error: ", e);
    }
  }
}
