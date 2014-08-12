package com.tieto.it2014.dao.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FRIENDS")
public class FriendJpa {
    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "UserID")
    private String userId;

    @Column(name = "FriendID")
    private String friendId;
}
