package com.technique.jersey.configs;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.multibindings.Multibinder;
import java.io.IOException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ClubsOSResourceModule extends AbstractModule {

  @Override
  protected void configure() {
    requestStaticInjection(ClubOSResourceConfig.class);
    Multibinder<Object> multibinder = Multibinder
        .newSetBinder(binder(), Object.class, ClubOSResource.class);

    multibinder.addBinding().to(Key.get(JacksonJaxbJsonProvider.class));
  }

  @Provides
  public JacksonJaxbJsonProvider getJacksonJaxbJsonProvider(ObjectMapper objectMapper) {
    JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();

    JodaModule jodaModule = new JodaModule();
    jodaModule.addSerializer(LocalDate.class, new JsonSerializer<LocalDate>() {
      final DateTimeFormatter parser = DateTimeFormat.forPattern("yyyy-MM-dd");

      @Override
      public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider)
          throws IOException {
        jgen.writeString(parser.print(value));
      }
    });

    objectMapper.registerModule(jodaModule);
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    provider.setMapper(objectMapper);

    return provider;
  }
}
