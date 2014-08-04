package com.tieto.it2014.dao.user;

import com.tieto.it2014.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class UserEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR_OF_BIRTH")
    private Integer yearOfBirth;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.id;
        this.name = user.name;
        this.yearOfBirth = user.yearOfBirth;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getYearOfBirth() { return yearOfBirth; }

    public User toUser() {
        return new User(this.id, this.name, this.yearOfBirth);
    }

}
