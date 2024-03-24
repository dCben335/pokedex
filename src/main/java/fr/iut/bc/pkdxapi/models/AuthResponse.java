package fr.iut.bc.pkdxapi.models;

import fr.iut.bc.pkdxapi.models.User.UserResponse;

// AuthResponse.java
public class AuthResponse {
    private String token;
    private UserResponse user;

    public AuthResponse(String token, UserResponse user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}

