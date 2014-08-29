package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserAchievmentDate")
public class UserAchievmentJpa implements JpaEntity<UserAchievement>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "achievment_id")
    private int achievmentId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "complete_message")
    private String completeMessage;

    @Column(name = "imei")
    private String imei;

    @Column(name = "date")
    private Long date;

    @Column(name = "isSeen")
    private Boolean isSeen;

    @Column(name = "isNew")
    private Boolean isNew;

    @Override
    public UserAchievement toDomain() {
        return new UserAchievement(this.achievmentId, this.name, this.description, this.completeMessage, this.imei, this.date, this.isNew, this.isSeen);
    }

}
