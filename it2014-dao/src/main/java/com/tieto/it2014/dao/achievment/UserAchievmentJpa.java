package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String complete_message;

    @Column(name = "imei")
    private String imei;

    @Column(name = "date")
    private Long date;

    public UserAchievmentJpa() {
    }

    public UserAchievmentJpa(UserAchievement achievment) {
        this.achievmentId = achievment.getAchievmentId();
        this.name = achievment.getName();
        this.description = achievment.getDescription();
        this.complete_message = achievment.getCompleteMessage();
        this.imei = achievment.getImei();
        this.date = achievment.getDate();
    }

    @Override
    public UserAchievement toDomain() {
        return new UserAchievement(this.achievmentId, this.name, this.description, this.complete_message, this.imei, this.date);
    }

    public int getAchievmentId() {
        return achievmentId;
    }

    public void setAchievmentId(int achievmentId) {
        this.achievmentId = achievmentId;
    }

}
