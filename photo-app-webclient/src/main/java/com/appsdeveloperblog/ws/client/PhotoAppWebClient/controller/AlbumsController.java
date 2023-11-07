package com.appsdeveloperblog.ws.client.PhotoAppWebClient.controller;

import com.appsdeveloperblog.ws.client.PhotoAppWebClient.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumsController {

    @Autowired
    OAuth2AuthorizedClientService oauth2ClientService;
    @Autowired
    WebClient webClient;

    @GetMapping
    public String getAlbums(
            Model model,
            @AuthenticationPrincipal OidcUser principal
    ) {
        String url = "http://localhost:9001/albums";

        AlbumRest[] albumsResponse = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(AlbumRest[].class)
                .block();

        if(albumsResponse == null)
            throw new RuntimeException();

        model.addAttribute("albums", Arrays.asList(albumsResponse));

        return "albums";
    }
}
