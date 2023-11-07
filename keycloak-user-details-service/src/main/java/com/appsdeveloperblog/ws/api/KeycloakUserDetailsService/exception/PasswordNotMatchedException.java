package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
