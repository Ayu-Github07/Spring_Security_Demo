package com.example.spring_security_with_mongodb.models;

public class AuthenticationResponse {

    private String response;

    public AuthenticationResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
