package com.technique.code.challenge.checkins;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.technique.jersey.configs.ClubOSResourceConfig;
import com.technique.jersey.configs.ClubsOSResourceModule;
import java.net.URI;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class CodeChallengeApp {

  private static final String BASE_URI = "http://0.0.0.0:9999/api/";

  public static void main(String[] args) {
    System.setProperty("env", "prod");
    Injector injector = Guice.createInjector(
        new AbstractModule() {
          @Override
          protected void configure() {
            install(new CodeChallengeAppModule());
            install(new ClubsOSResourceModule());
          }
        }
    );
    final CodeChallengeApp instance = injector
        .getInstance(CodeChallengeApp.class);
    final HttpServer server = instance.startServer();
    System.out.println(String.format("Jersey app started with WADL available at "
        + "%sapplication.wadl\nHit ctrl-c to stop it...", BASE_URI));
  }

  private HttpServer startServer() {

    Logger l = Logger.getLogger("org.glassfish.grizzly.http.server.HttpHandler");
    l.setLevel(Level.FINE);
    l.setUseParentHandlers(false);
    ConsoleHandler ch = new ConsoleHandler();
    ch.setLevel(Level.ALL);
    l.addHandler(ch);
    ServiceLocator sl = ServiceLocatorUtilities.createAndPopulateServiceLocator();
    ClubOSResourceConfig config = sl.create(ClubOSResourceConfig.class);

    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config, sl);
  }
}
