package com.technique.code.challenge.checkins.api;

import com.google.inject.Inject;
import com.technique.code.challenge.checkins.service.CheckinService;
import com.technique.code.challenge.checkins.service.Checkin;
import com.technique.code.challenge.checkins.service.Member;
import com.technique.code.challenge.checkins.service.MemberService;
import com.technique.jersey.configs.DataWrapper;
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
  @Produces(MediaType.APPLICATION_JSON)
  public DataWrapper getCheckins(@QueryParam("locationId") final Integer locationId) {
    final List<Checkin> checkins = checkinService.getCheckins(locationId, LocalDate.now());
    return new DataWrapper<>(checkins);
  }

  @GET
  @Path("/members")
  @Produces(MediaType.APPLICATION_JSON)
  public DataWrapper getMembers(@QueryParam("locationId") final Integer locationId) {
    final List<Member> members = memberService.getMembers(locationId);
    return new DataWrapper<>(members);
  }
}