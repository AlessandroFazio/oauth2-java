package com.appsdeveloperblog.ws.client.OrdersWebOAuthClient.controller;

import com.appsdeveloperblog.ws.client.OrdersWebOAuthClient.response.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public String getOrders(
            Model model,
            @RegisteredOAuth2AuthorizedClient("users-click-client-oidc")
            OAuth2AuthorizedClient authorizedClient
    ) {
        String jwtAccessToken = authorizedClient.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + jwtAccessToken);

        String url = "http://localhost:8095/orders";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Order>> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, entity,
                        new ParameterizedTypeReference<List<Order>>() {});

        List<Order> orders = responseEntity.getBody();
        model.addAttribute("orders", orders);

        return "orders-page";
    }
}
