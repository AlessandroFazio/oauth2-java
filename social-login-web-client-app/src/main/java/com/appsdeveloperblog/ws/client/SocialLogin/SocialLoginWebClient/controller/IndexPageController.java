package com.appsdeveloperblog.ws.client.SocialLogin.SocialLoginWebClient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexPageController {

    @GetMapping
    public String displayIndexPage(Model model) {
        return "index";
    }
}
