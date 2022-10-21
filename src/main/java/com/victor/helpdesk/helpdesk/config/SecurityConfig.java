package com.victor.helpdesk.helpdesk.config;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.victor.helpdesk.helpdesk.security.JWTAuthenticationFilter;
import com.victor.helpdesk.helpdesk.security.JWTAuthorizationFilter;
import com.victor.helpdesk.helpdesk.security.JWTUtil;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

  private static final String[] PUBLIC_MATCHERS = { "/h2/**" };

  @Autowired
  private Environment env;

  @Autowired
  private JWTUtil jwtUtil;

  @Autowired
  private UserDetailsService detailsService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

    if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
      http.headers().frameOptions().disable();
    }

    http.cors().and().csrf().disable();

    http.addFilter(new JWTAuthenticationFilter(authManager, jwtUtil));

    http.addFilter(new JWTAuthorizationFilter(authManager, jwtUtil, detailsService));

    http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().anyRequest().authenticated();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();

  }

  @Bean
  AuthenticationManager authManager(HttpSecurity http) throws Exception {

    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(detailsService)
        .passwordEncoder(bCryptPasswordEncoder())
        .and()
        .build();

  }

  @Bean
  CorsConfigurationSource configurationSource() {

    CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
    configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/", configuration);
    return source;

  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
