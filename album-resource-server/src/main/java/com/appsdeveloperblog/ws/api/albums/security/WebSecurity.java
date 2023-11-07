/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appsdeveloperblog.ws.api.albums.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@Configuration
public class WebSecurity {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> keycloakRoleConverter = new KeycloakRoleConverter();
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http
                .authorizeHttpRequests(authz ->
                        authz.requestMatchers(HttpMethod.GET, "/photos/**")
                        .hasAuthority("ROLE_developer")
                                .anyRequest()
                                .authenticated()
                )
                .oauth2ResourceServer(oaith2 ->
                        oaith2.jwt(jwtConfigurer ->
                                jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .sessionManagement(sessionMngConfigurer ->
                                sessionMngConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
