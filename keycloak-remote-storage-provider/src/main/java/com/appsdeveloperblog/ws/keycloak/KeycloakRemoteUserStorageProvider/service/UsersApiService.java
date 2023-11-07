package com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.service;

import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.response.User;
import com.appsdeveloperblog.ws.keycloak.KeycloakRemoteUserStorageProvider.response.VerifyPasswordResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
public interface UsersApiService {

    @GET
    @Path("/{username}")
    User getUserDetails(@PathParam("username") String username);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}/verify-password")
    VerifyPasswordResponse verifyUserPassword(@PathParam("username") String username, String password);
}
