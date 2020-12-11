package com.radhio.cvsender.Models;

/**
 * Created by Azmia Hoque Radhio on 12/11/2020.
 */
public class AccessToken {
    private String token;

    private boolean isAuthenticated;

    private String message;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
