package com.tieto.it2014.domain.achievment.entity;

import com.tieto.it2014.domain.achievment.Entity;

public class UserAchievement extends Entity {

    private static final long serialVersionUID = 1L;

    private int id;
    private String userId;
    private int achievmentId;
    private Long date;

    public UserAchievement() {
        super(0);
    }

    public UserAchievement(String userId, int achievmentId, Long date, int id) {
        super(id);
        this.id = id;
        this.userId = userId;
        this.achievmentId = achievmentId;
        this.date = date;
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
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the achievmentId
     */
    public int getAchievmentId() {
        return achievmentId;
    }

    /**
     * @param achievmentId the achievmentId to set
     */
    public void setAchievmentId(int achievmentId) {
        this.achievmentId = achievmentId;
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

}
