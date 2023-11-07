package com.appsdeveloperblog.ws.api.SimpleResourceServer.response;

public record UserRest (
     String userFirstName,
     String userLastName,
     String userId
) {
}
