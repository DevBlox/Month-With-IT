package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class User extends Entity {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String email;
    private String imei;
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
        return getActivated();
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the imei
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * @return the activated
     */
    public Boolean getActivated() {
        return activated;
    }
}
