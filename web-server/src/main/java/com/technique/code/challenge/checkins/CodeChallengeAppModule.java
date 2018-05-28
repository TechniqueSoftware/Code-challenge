package com.technique.code.challenge.checkins;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.technique.code.challenge.checkins.api.CodeChallengeResource;
import com.technique.jersey.configs.ClubOSResource;
import com.technique.jersey.configs.ClubOSResourceConfig;

class CodeChallengeAppModule extends AbstractModule {

  @Override
  protected void configure() {
    requestStaticInjection(ClubOSResourceConfig.class);
    Multibinder<Object> multibinder = Multibinder
        .newSetBinder(binder(), Object.class, ClubOSResource.class);

    multibinder.addBinding().to(CodeChallengeResource.class);
  }
}
