package com.appsdeveloperblog.ws.api.KeycloakUserDetailsService.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
