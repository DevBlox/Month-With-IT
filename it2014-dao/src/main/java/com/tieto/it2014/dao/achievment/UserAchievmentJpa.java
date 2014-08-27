package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.achievment.entity.UserAchievement;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACHIEVMENT")
public class UserAchievmentJpa implements JpaEntity<UserAchievement>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "achievmentId")
    private int achievmentId;

    @Column(name = "date")
    private Long date;

    public UserAchievmentJpa() {
    }

    public UserAchievmentJpa(UserAchievement achievment) {
        this.id = achievment.getId();
        this.userId = achievment.getUserId();
        this.achievmentId = achievment.getAchievmentId();
        this.date = achievment.getDate();
    }

    @Override
    public UserAchievement toDomain() {
        return new UserAchievement(this.getUserId(), this.getAchievmentId(), this.getDate(), this.getId());
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @return the achievmentId
     */
    public int getAchievmentId() {
        return achievmentId;
    }

    /**
     * @return the date
     */
    public Long getDate() {
        return date;
    }

}
