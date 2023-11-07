package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class KeycloakUserDetailsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakUserDetailsServiceApplication.class, args);
	}

}
