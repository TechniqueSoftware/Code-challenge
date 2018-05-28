package com.technique.jersey.configs;

import com.google.inject.Injector;
import java.util.Set;
import javax.inject.Inject;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClubOSResourceConfig extends ResourceConfig {

  private final static Logger log = LoggerFactory.getLogger(ClubOSResourceConfig.class);

  @com.google.inject.Inject
  public static Injector INJECTOR;
  @com.google.inject.Inject
  @ClubOSResource
  public static Set<Object> BOUND_RESOURCES;
  @com.google.inject.Inject(optional = true)
  @ClubOSResource
  public static String PACKAGES;

  @Inject
  public ClubOSResourceConfig(ServiceLocator serviceLocator) {
    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);

    GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
    guiceBridge.bridgeGuiceInjector(INJECTOR);

    if (PACKAGES != null) {
      packages(true, PACKAGES);
    }

    for (Object obj : BOUND_RESOURCES) {
      log.info("Registering {}", obj);
      register(obj);
    }
  }
}
