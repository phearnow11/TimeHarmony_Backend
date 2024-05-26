package com.example.TimeHarmony.controller;

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
    public String token(Authentication authentication) {
        return TOKEN_SERVIVE.generateToken(authentication);
    }
}
