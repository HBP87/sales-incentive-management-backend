package com.accolite.sim.dto;

import java.util.List;

public class JwtResponse {
    String token;
    List<String> authorities;

    public JwtResponse() {
    }

    public JwtResponse(String token, List<String> authorities) {

        this.token = token;
        this.authorities = authorities;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
