package com.appsdeveloperblog.ws.api.SimpleResourceServer.controller;

import com.appsdeveloperblog.ws.api.SimpleResourceServer.response.UserRest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working...";
    }

    //@Secured("ROLE_developer")
    @PreAuthorize("hasAnyAuthority('ROLE_developer') or #id == #jwt.subject")
    @DeleteMapping(path = "/{id}")
    public String deleteUser(
            @PathVariable String id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return "Deleted user with id " + id + " and jwt subject " + jwt.getSubject();
    }

    @PostAuthorize("returnObject.userId() == #jwt.subject")
    @GetMapping(path = "/{id}")
    public UserRest getUserDetails(
            @PathVariable String id,
            @AuthenticationPrincipal Jwt jwt
    ) {
        return new UserRest("Marco", "Giannini", jwt.getSubject());
    }
}
