package com.technique.code.challenge.checkins.service;

import com.fasterxml.jackson.databind.JavaType;
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
import javax.inject.Singleton;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class FileCheckinDataLoader implements CheckinDataLoader {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  @Override
  public Map<Integer, Map<LocalDate, List<Checkin>>> buildInMemoryCheckinMap() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JodaModule());

    final File file = new File("/tmp/checkinData.txt");
    try {
      final List<String> strings = Files.readLines(file, Charsets.UTF_8);

      for (final String string : strings) {
        try {
          final TypeFactory factory = TypeFactory.defaultInstance();
          final CollectionType checkinListType = factory
              .constructCollectionType(List.class, Checkin.class);
          final JavaType innerMap = factory
              .constructMapType(HashMap.class, factory.constructType(LocalDate.class),
                  checkinListType);
          final MapType outterMap = factory
              .constructMapType(HashMap.class, factory.constructType(Integer.class), innerMap);
          return objectMapper.readValue(string, outterMap);
        } catch (IOException e) {
          logger.error("bad file entry for checkins");
        }
      }
    } catch (Exception e) {
      logger.error("Error: {}", e);
    }
    return null;
  }
}
