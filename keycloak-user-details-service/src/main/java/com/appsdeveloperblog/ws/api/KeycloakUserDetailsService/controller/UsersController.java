package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.controller;

import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception.PasswordNotMatchedException;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception.ResourceNotFoundException;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response.UserRest;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response.VerifyPasswordResponse;
import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.service.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    UsersServiceImpl usersService;

    @GetMapping("/{userName}")
    public UserRest getUser(
            @PathVariable("userName") String userName
    ) {
        UserRest userRest;

        try {
            userRest = usersService.getUserDetails(userName);

        } catch (ResourceNotFoundException e) {
            LOGGER.error("Unable to find resource.", e);
            return null;
        }

        return userRest;
    }

    @PostMapping("/{userName}/verify-password")
    VerifyPasswordResponse verifyUserPassword(
        @PathVariable("userName") String userName,
        @RequestBody String password
    ) {
        boolean result = false;

        try {
            UserRest user = usersService.getUserDetails(userName, password);
            result = true;
            LOGGER.info("Password Matched!");

        } catch (ResourceNotFoundException e) {
            LOGGER.error("Resource not found", e);
            return null;

        } catch (PasswordNotMatchedException e) {
            LOGGER.error("Password didn't match", e);
            return new VerifyPasswordResponse.VerifyPasswordResponseBuilder()
                    .setResult(result)
                    .build();
        }
        return new VerifyPasswordResponse.VerifyPasswordResponseBuilder()
                .setResult(result)
                .build();
    }
}
