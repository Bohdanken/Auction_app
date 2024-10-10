package com.application.auction.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${trusted.ip.address}")
    private String trustedIpAddress;

    @Value("${localhost.ip.address}")
    private String localhostIpAddress;

    @Value("${localhost.ipv6.address}")
    private String localhostIpv6Address;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/bid", "/submit_bid", "/auction-updates", "/lot/**", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/main")
                        .access(new WebExpressionAuthorizationManager("hasIpAddress('" + localhostIpAddress + "') or hasIpAddress('" + localhostIpv6Address + "') or hasIpAddress('" + trustedIpAddress + "')"))
                        .anyRequest().permitAll()
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf().disable();

        return http.build();
    }
}
