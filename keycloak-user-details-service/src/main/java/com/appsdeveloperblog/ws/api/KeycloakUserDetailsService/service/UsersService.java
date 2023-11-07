package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.service;

import com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.response.UserRest;

public interface UsersService {
    UserRest getUserDetails(String userName);
    UserRest getUserDetails(String userEmail, String password);

}
