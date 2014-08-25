package com.tieto.it2014.dao.user;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "FRIENDS")
public class FriendJpa implements JpaEntity<User> {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "FriendID")
    private String friendId;

    public String getFriendId() {
        return this.friendId;
    }

    public Integer getId() {
        return this.id;
    }

    public FriendJpa() {

    }

    public FriendJpa(String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }

    @Override
    public User toDomain() {
        return null;
    }
}
