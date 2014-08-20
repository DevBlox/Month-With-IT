//package com.tieto.it2014.dao;
//
//import com.tieto.it2014.dao.user.*;
//import com.tieto.it2014.dao.JpaEntity;
//import com.tieto.it2014.domain.user.entity.User;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "WEIGHT")
//public class WeightJpa implements JpaEntity<User> {
//
//    @Id
//    @Column(name = "userId")
//    private String userId;
//
//    @Column(name = "weight")
//    private String weight;
//
//    @Column(name = "created")
//    private String timestamp;
//
//    @Column(name = "id")
//    private String password;
//
//    public WeightJpa() {
//    }
//
//    public WeightJpa(User user) {
//        this.imei = user.imei;
//        this.email = user.email;
//        this.username = user.username;
//        this.password = user.password;
//    }
//
//    public String getImei() {
//        return imei;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public User toDomain() {
//        return new User(this.imei, this.username, this.password, this.email);
//    }
//
//}
