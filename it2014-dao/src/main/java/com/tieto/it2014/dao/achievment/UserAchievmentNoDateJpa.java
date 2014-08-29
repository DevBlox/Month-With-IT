package com.tieto.it2014.dao.achievment;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.achievment.entity.UserAchievementNoDate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_ACHIEVMENT")
public class UserAchievmentNoDateJpa implements JpaEntity<UserAchievementNoDate>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "userId")
    private String imei;

    @Column(name = "achievmentId")
    private int achievementId;

    @Column(name = "date")
    private Long date;

    @Column(name = "isSeen")
    private Boolean isSeen;

    @Column(name = "isNew")
    private Boolean isNew;

    public UserAchievmentNoDateJpa() {
    }

    public UserAchievmentNoDateJpa(UserAchievementNoDate achievement) {
        this.achievementId = achievement.getAchievmentId();
        this.imei = achievement.getUserId();
        this.date = achievement.getDate();
        this.isNew = achievement.getIsNew();
        this.isSeen = achievement.getIsSeen();
    }

    @Override
    public UserAchievementNoDate toDomain() {
        return new UserAchievementNoDate(this.getId(), this.getAchievementId(), this.getDate(), this.getImei(), this.isNew, this.isSeen);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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
     * @return the achievementId
     */
    public int getAchievementId() {
        return achievementId;
    }

    /**
     * @param achievementId the achievementId to set
     */
    public void setAchievementId(int achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * @return the date
     */
    public Long getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Long date) {
        this.date = date;
    }

    /**
     * @return the isSeen
     */
    public boolean getIsSeen() {
        return isSeen;
    }

    /**
     * @param isSeen the isSeen to set
     */
    public void setIsSeen(Boolean isSeen) {
        this.isSeen = isSeen;
    }

    /**
     * @return the isNew
     */
    public boolean getIsNew() {
        return isNew;
    }

    /**
     * @param isNew the isNew to set
     */
    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

}
