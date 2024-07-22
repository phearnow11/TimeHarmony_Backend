package com.example.TimeHarmony.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.TimeHarmony.builder.MemberBuilder;
import com.example.TimeHarmony.entity.Members;
import com.example.TimeHarmony.entity.Users;
import com.example.TimeHarmony.service.EmailService;
import com.example.TimeHarmony.service.MemberService;
import com.example.TimeHarmony.service.StringService;
import com.example.TimeHarmony.service.TokenService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final TokenService TOKEN_SERVIVE;
    private final String DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE = "Email Verification Code";
    private final String DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD = "Password Changing Verification Code";

    @Autowired
    private MemberService MEMBER_SERVICE;

    @Autowired
    private StringService STRING_SERVICE;

    @Autowired
    private EmailService EMAIL_SERVICE;

    public AuthController(TokenService tokenService) {
        this.TOKEN_SERVIVE = tokenService;
    }

    @PostMapping("login")
    public Map<String, Object> token(Authentication authentication) {
        Members m = MEMBER_SERVICE.getMemberbyUserLogInfo(MEMBER_SERVICE.getUserbyUsername(authentication.getName()));
        Map<String, Object> data = new Hashtable<>();
        String token = TOKEN_SERVIVE.generateToken(authentication);
        data.put("token", token);
        data.put("user", m.getMember_id());
        MEMBER_SERVICE.login(m.getMember_id().toString());
        return data;
    }

    @PostMapping("logout")
    public String logout(@RequestParam("member_id") String member_id, @RequestBody Map<String, Object> data) {
        // Gson gson = new Gson();
        // CART_SERVICE.saveChecked(gson.toJsonTree(data.get("checks")));
        MEMBER_SERVICE.logout(member_id);
        return "Logout";
    }

    @GetMapping("/debug/jwt")
    public Map<String, Object> getPrincipalInfo(JwtAuthenticationToken principal) {

        Collection<String> authorities = principal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Map<String, Object> info = new Hashtable<>();
        info.put("name", principal.getName());
        info.put("authorities", authorities);
        info.put("tokenAttributes", principal.getTokenAttributes());

        return info;
    }

    @RequestMapping(value = "login/google", method = RequestMethod.POST)
    public Map<String, Object> getProfileDetailsGoogle(@RequestParam("token") String accessToken) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setBearerAuth(accessToken);

            HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

            String url = "https://www.googleapis.com/oauth2/v2/userinfo";
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            JsonObject jsonObject = new Gson().fromJson(response.getBody(), JsonObject.class);
            String email = jsonObject.get("email").getAsString();
            String given_name = jsonObject.get("given_name").getAsString();
            String family_name = jsonObject.get("family_name").getAsString();
            String picture = "https://files.catbox.moe/n1w3b0.png";
            String tmpusername = jsonObject.get("id").getAsString();
            String tmppassword = tmpusername;
            Members nMembers = MEMBER_SERVICE.getMemberbyEmail(email);
            if (nMembers == null) {
                byte ACTIVATE = 1;
                Users nUsers = new Users(tmpusername, tmppassword, null, ACTIVATE);

                nMembers = new MemberBuilder().setEmail(email)
                        .setUserLogInfo(nUsers)
                        .setFirstName(given_name)
                        .setLastName(family_name)
                        .setMemberImage(picture)
                        .build();
                MEMBER_SERVICE.saveUser(nMembers, nUsers);
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    nMembers.getUser_log_info().getUsername(), nMembers.getUser_log_info().getPassword());

            return token(authentication);
        } catch (Exception e) {
            Map<String, Object> errors = new HashMap<>();
            errors.put("error", e);
            return errors;
        }
    }

    @RequestMapping(value = "verify/{type}/getcode", method = RequestMethod.GET)
    public String updateEmailVerificationCode(@RequestParam("email") String email, @PathVariable("type") String type) {
        String code = STRING_SERVICE.autoGenerateString(6);

        String subject_mail = "";
        switch (type) {
            case "google":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_GOOGLE;
                break;
            case "password":
                subject_mail = DEFAULT_MAIL_SUBJECT_VERIFY_PASSWORD;
                break;
            default:
                break;
        }

        EMAIL_SERVICE.sendSimpleMessage(email, subject_mail, code);
        return code;
    }

}
