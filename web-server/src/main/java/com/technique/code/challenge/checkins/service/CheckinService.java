package com.technique.code.challenge.checkins.service;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

@Singleton
public class CheckinService {

  @Inject
  CheckinService() {
  }

  public List<Checkin> getCheckins(final Integer locationId, final LocalDate checkinDate) {
    return Arrays.asList(new Checkin(1, DateTime.now(DateTimeZone.UTC)));
  }
}
