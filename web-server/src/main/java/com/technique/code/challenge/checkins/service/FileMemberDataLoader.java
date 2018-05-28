package com.technique.code.challenge.checkins.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileMemberDataLoader implements MemberDataLoader {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Map<Integer, List<Member>> buildInMemoryMemberMap() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JodaModule());

    final File file = new File("/tmp/memberData.txt");
    try {
      final List<String> strings = Files.readLines(file, Charsets.UTF_8);

      for (final String string : strings) {
        try {
          final TypeFactory factory = TypeFactory.defaultInstance();
          final CollectionType memberListType = factory
              .constructCollectionType(List.class, Member.class);
          final MapType outterMap = factory
              .constructMapType(HashMap.class, factory.constructType(Integer.class),
                  memberListType);
          return objectMapper.readValue(string, outterMap);
        } catch (IOException e) {
          logger.error("bad file entry for members");
        }
      }
    } catch (Exception e) {
      logger.error("Error: {}", e);
    }
    return null;
  }
}
