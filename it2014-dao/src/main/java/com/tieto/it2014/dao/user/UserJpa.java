package com.tieto.it2014.dao.user;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserJpa implements JpaEntity<User> {

    @Id
    @Column(name = "IMEI")
    private String imei;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "ACTIVATED")
    private Boolean activated;

    public UserJpa() {
    }

    public UserJpa(User user) {
        this.imei = user.imei;
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
        this.token = user.getToken();
        this.activated = user.isActivated();
    }

    public String getImei() {
        return imei;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Boolean getActivated() {
        return activated;
    }

    @Override
    public User toDomain() {
        return new User(this.imei, this.username, this.password, this.email, this.token, this.activated);
    }

}
