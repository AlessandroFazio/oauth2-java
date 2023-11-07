package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.db_init;

import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.entity.User;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseInitializer {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {

        User user = new User (
            "Alessandro",
            "Fazio",
            "fazioale2000@gmail.com",
            bCryptPasswordEncoder.encode("password")
        );

        usersRepository.save(user);
    }
}
