package com.example.TimeHarmony.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.TokenService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final TokenService TOKEN_SERVIVE;

    @Autowired
    private MemberService MEMBER_SERVICE;

    public AuthController(TokenService tokenService) {
        this.TOKEN_SERVIVE = tokenService;
    }

    @PostMapping("login")
    public Map<String, Object> token(Authentication authentication) {
        Members m = MEMBER_SERVICE.getMemberbyUserLogInfo(MEMBER_SERVICE.getUserbyUsername(authentication.getName()));
        Map<String, Object> data = new HashMap<>();
        String token = TOKEN_SERVIVE.generateToken(authentication);
        data.put("token", token);
        data.put("user", m.getMember_id());
        MEMBER_SERVICE.login(m.getMember_id().toString());
        return data;
    }

    @PostMapping("logout")
    public String logout(@RequestParam("member_id") String member_id) {
        MEMBER_SERVICE.logout(member_id);
        return "Logout";
    }

    @GetMapping("/debug/jwt")
    public Map<String, Object> getPrincipalInfo(JwtAuthenticationToken principal) {

        Collection<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> info = new HashMap<>();
        info.put("name", principal.getName());
        info.put("authorities", authorities);
        info.put("tokenAttributes", principal.getTokenAttributes());

        return info;
    }

    @RequestMapping(value = "login/google", method = RequestMethod.POST)
    public String googleLogin() {

        return "";
    }
}
