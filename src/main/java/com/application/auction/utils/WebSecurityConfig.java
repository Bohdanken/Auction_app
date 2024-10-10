package com.application.auction.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.access.IpAddressAuthorizationManager.hasIpAddress;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String TRUSTED_IP_ADDRESS = "192.168.0.90"; // Replace with your computer's IP address
    private static final String LOCALHOST_IP_ADDRESS = "127.0.0.1";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/bid", "/submit_bid", "/auction-updates", "/lot/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/main").access(hasIpAddress(LOCALHOST_IP_ADDRESS))
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .csrf().disable();

        return http.build();
    }
}
