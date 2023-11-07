package com.appsdeveloperblog.ws.api.SimpleResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurity {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        Converter<Jwt, Collection<GrantedAuthority>> keycloakRoleConverter = new KeycloakRoleConverter();
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedHeaders(List.of("Authorization"));
        configuration.setAllowedOrigins(List.of("http://localhost:8012"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        // Configure Http Security
        http
                .cors(cors -> cors
                        .configurationSource(configurationSource()))
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers(HttpMethod.GET, "/users/status/check")
                                // .hasAnyAuthority("SCOPE_profile")
                                .hasAnyRole("developer", "admin")
                                .anyRequest()
                                .authenticated())
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwt -> {
                            jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
                        })
        );
        return http.build();
    }
}
