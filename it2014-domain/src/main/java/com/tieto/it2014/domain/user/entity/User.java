package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    public String username;
    public String password;
    public String email;
    public String imei;

    public User() {
        super(null);
    }
   
    public User(String imei, String username, String password, String email) {
        super(imei);
        this.username = username;
        this.password = password;
        this.email = email;
        this.imei = imei;
    }
}
