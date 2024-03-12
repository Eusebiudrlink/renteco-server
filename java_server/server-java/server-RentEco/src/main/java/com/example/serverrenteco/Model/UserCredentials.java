package com.example.serverrenteco.Model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserCredentials {


    private String username;
    private String password;

    public UserCredentials() {
    }

    @JsonCreator(mode = JsonCreator.Mode.DEFAULT)
    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}