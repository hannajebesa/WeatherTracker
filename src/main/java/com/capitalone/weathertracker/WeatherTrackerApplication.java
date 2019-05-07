package com.capitalone.weathertracker;

import com.capitalone.weathertracker.statistics.Statistic;
import com.capitalone.weathertracker.strategy.StatisticStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WeatherTrackerApplication implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
  private static final int DEFAULT_PORT = 8000; // this is what HackerRank expects

  public static void main(String[] args) {
    SpringApplication.run(WeatherTrackerApplication.class, args);
  }

  @Override
  public void customize(TomcatServletWebServerFactory factory) {
    factory.setPort(getServerPort());
  }

  private int getServerPort() {
    try {
      return Integer.parseInt(System.getenv("PORT"));
    } catch (NumberFormatException e) {
      return DEFAULT_PORT;
    }
  }
}
