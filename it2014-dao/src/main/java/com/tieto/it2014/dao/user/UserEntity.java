package com.tieto.it2014.dao.user;

import com.tieto.it2014.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Column(name = "IMEI")
    @Id
    private String imei;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PASSWORD")
    private String password;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.imei = user.imei;
        this.email = user.email;
        this.username = user.username;
        this.password = user.password;
    }

    public String getImei() { return imei; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public User toUser() {
        return new User(this.imei, this.username, this.password, this.email);
    }

}
