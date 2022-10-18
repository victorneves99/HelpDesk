package com.victor.helpdesk.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.victor.helpdesk.helpdesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

  @Autowired
  private DBService dbService;

  @Bean
  public void instaciaDB() {
    this.dbService.instanciaDb();
  }

}
