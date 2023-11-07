package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.service;

import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.entity.User;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception.PasswordNotMatchedException;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception.ResourceNotFoundException;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.repository.UsersRepository;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response.UserRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserRest getUserDetails(String userEmail)
            throws ResourceNotFoundException {

        User user = usersRepository.findByEmail(userEmail);

        if(user == null) throw new ResourceNotFoundException("Unable to find resource.");

        return new UserRest.UserRestBuilder()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastname())
                .build();
    }

    @Override
    public UserRest getUserDetails(String userEmail, String password)
            throws ResourceNotFoundException, PasswordNotMatchedException {

        User user = usersRepository.findByEmail(userEmail);

        if(user == null) throw new ResourceNotFoundException("Unable to find resource.");

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new PasswordNotMatchedException("Password doesn't match");

        return new UserRest.UserRestBuilder()
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastname())
                .build();
    }
}
