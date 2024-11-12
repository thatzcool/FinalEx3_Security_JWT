package com.ssg.finalex3.config;


import lombok.extern.log4j.Log4j2;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.http.SessionCreationPolicy;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.security.web.SecurityFilterChain;
 import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 import org.springframework.web.cors.CorsConfiguration;
 import org.springframework.web.cors.CorsConfigurationSource;
 import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 import com.ssg.finalex3.member.security.filter.JWTCheckFilter;

  import java.util.List;

        @Configuration
 @EnableMethodSecurity
 public class CustomSecurityConfig {


          private JWTCheckFilter jwtCheckFilter;

         @Autowired
  private void setJwtCheckFilter(JWTCheckFilter jwtCheckFilter) {
             this.jwtCheckFilter = jwtCheckFilter;
          }
034:
        035:
        036:   @Bean
037:   public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        038:
        039:     log.info("filter chain............");
        040:
        041:     httpSecurity.formLogin(httpSecurityFormLoginConfigurer -> {
            042:       httpSecurityFormLoginConfigurer.disable();
            043:
            044:     });
        045:
        046:     httpSecurity.logout( config -> config.disable());
        047:
        048:     httpSecurity.csrf(config -> { config.disable();});
        049:
        050:     httpSecurity.sessionManagement(sessionManagementConfigurer -> {
            051:       sessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
            052:     });
        053:
        054:     httpSecurity.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);
        055:
        056:     httpSecurity.cors(cors -> {
            057:       cors.configurationSource(corsConfigurationSource());
            058:     });
        059:
        060:     return httpSecurity.build();
        061:   }

           @Bean
  public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
          }

         @Bean
  public CorsConfigurationSource corsConfigurationSource() {

             CorsConfiguration corsConfiguration = new CorsConfiguration();

            corsConfiguration.setAllowedOriginPatterns(List.of("*")); // 어디서든 허락
             corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
            corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
            corsConfiguration.setAllowCredentials(true);

           UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
             source.registerCorsConfiguration("/**", corsConfiguration);

             return source;
         }

         }
