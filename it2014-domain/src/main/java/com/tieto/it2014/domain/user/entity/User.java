package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    public String username;
    public String password;
    public String email;
    public String imei;
    private String token;
    private Boolean activated;

    public User() {
        super(null);
    }
   
    public User(String imei, String username, String password, String email, String token, Boolean activated) {
        super(imei);
        this.username = username;
        this.password = password;
        this.email = email;
        this.imei = imei;
        this.token = token;
        this.activated = activated;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
