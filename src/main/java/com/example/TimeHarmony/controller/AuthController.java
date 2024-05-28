package com.example.TimeHarmony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.service.TokenService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final TokenService TOKEN_SERVIVE;

    public AuthController(TokenService tokenService) {
        this.TOKEN_SERVIVE = tokenService;
    }

    @PostMapping("token")
    public Map<String, String> token(Authentication authentication) {
        Map<String, String> data = new HashMap<>();
        data.put("token", TOKEN_SERVIVE.generateToken(authentication));
        data.put("user", null);
        System.out.println(authentication.getPrincipal());
        return data;
    }
}
