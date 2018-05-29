package com.technique.code.challenge.checkins.api;

import static com.technique.code.challenge.checkins.service.Constants.BASE_CHECKIN_DATE;

import com.google.inject.Inject;
import com.technique.code.challenge.checkins.service.Checkin;
import com.technique.code.challenge.checkins.service.CheckinService;
import com.technique.code.challenge.checkins.service.Member;
import com.technique.code.challenge.checkins.service.MemberService;
import com.technique.jersey.configs.DataWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
@Api(value = "Basic", description = "Base API")
public class CodeChallengeResource {

  private final Logger logger = LoggerFactory.getLogger(getClass());
  private final CheckinService checkinService;
  private final MemberService memberService;

  @Inject
  public CodeChallengeResource(final CheckinService checkinService,
      final MemberService memberService) {
    this.checkinService = checkinService;
    this.memberService = memberService;
  }

  @GET
  @Path("/checkins")
  @ApiOperation(value = "Retrieve checking for a specific location and for a specific date. All times and locations are UTC timezone.", response = DataWrapper.class)
  @Produces(MediaType.APPLICATION_JSON)
  public DataWrapper getCheckins(
      @ApiParam(value = "Location ID", defaultValue = "1") @QueryParam("locationId") final Integer locationId,
      @ApiParam(value = "Local Date", defaultValue = "2018-01-01") @QueryParam("dateToCheck") final String dateToCheck) {
    if (locationId == null || dateToCheck == null) {
      final DataWrapper errorResponse = new DataWrapper(null);
      errorResponse.setError("dateToCheck and locationId are required query parameters");
      return errorResponse;
    }
    final LocalDate checkinDate = new LocalDate(dateToCheck);
    if (checkinDate.isBefore(BASE_CHECKIN_DATE) || checkinDate.isAfter(
        BASE_CHECKIN_DATE.plusDays(90))) {
      final DataWrapper errorResponse = new DataWrapper(null);
      errorResponse.setError("only dates between 2018-01-01 and are valid dates fo"
          + "r dateToCheck query parameter");
      return errorResponse;
    }

    final List<Checkin> checkins = checkinService.getCheckins(locationId, checkinDate);
    return new DataWrapper<>(checkins);
  }

  @GET
  @Path("/members")
  @ApiOperation(value = "Retrieve members for a specific location", response = DataWrapper.class)
  @Produces(MediaType.APPLICATION_JSON)
  public DataWrapper getMembers(
      @ApiParam(value = "Location ID", defaultValue = "1") @QueryParam("locationId") final Integer locationId) {
    if (locationId == null) {
      final DataWrapper errorResponse = new DataWrapper(null);
      errorResponse.setError("locationId is a required query parameter");
      return errorResponse;
    }
    final List<Member> members = memberService.getMembers(locationId);
    return new DataWrapper<>(members);
  }

  class RequestError {

    private final String error;

    RequestError(final String error) {
      this.error = error;
    }

    public String getError() {
      return error;
    }
  }
}