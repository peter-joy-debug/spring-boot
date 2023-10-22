package com.example.entity;

import jakarta.persistence.Entity;

@Entity
public class UserSession {


    private String authToken;

    public UserSession() {
        // Default constructor
    }

    public UserSession(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }


    @Override
    public String toString() {
        return "UserSession{" +
                ", authToken='" + authToken + '\'' +
                '}';
    }

}
