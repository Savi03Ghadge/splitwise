package com.demo.splitwise.model;

public class JwtResponse {
    private String jwtToken;

    public JwtResponse(String token) {
        setJwtToken(token);
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
