package com.example.TimeHarmony.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.ToString;

@Entity
@ToString
public class Users {

    @Id
    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "username")
    @JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer", "username" }, allowSetters = true)
    private Authorities authorities;

    private byte enabled;

    public Users(String username, String password, Authorities authorities, byte enabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    public Users() {
    }

    public Authorities getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }

    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
