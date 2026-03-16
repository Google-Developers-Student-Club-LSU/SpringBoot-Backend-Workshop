package com.example.springworkshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    
    @Bean 
    SecurityFilterChain filterChain (HttpSecurity http, SessionAuthFilter sessionAuthFilter) {
          http
          .csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(
           auth -> auth
           .requestMatchers("/user/register", "/user/login").permitAll()
           .anyRequest().authenticated() 
          )
          .addFilterBefore(sessionAuthFilter, UsernamePasswordAuthenticationFilter.class);




        return http.build();
        
    }
}
