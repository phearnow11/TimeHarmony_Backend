package com.example.TimeHarmony.configuration;

import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.key}")
    private String jwtKey;

    @Bean
    JdbcUserDetailsManager user(DataSource dataSource, PasswordEncoder passwordEncoder) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())  // Enable CORS
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/token").permitAll()  // Allow access to the token endpoint without authentication
                .requestMatchers("/admin/**").authenticated()
                .requestMatchers(HttpMethod.POST).permitAll()
                .requestMatchers(HttpMethod.GET).permitAll()
                .anyRequest().hasAuthority("SCOPE_READ"))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    @Bean
    JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA512");
        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
