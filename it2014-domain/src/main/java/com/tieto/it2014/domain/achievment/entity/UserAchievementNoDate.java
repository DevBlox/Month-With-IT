package com.tieto.it2014.domain.achievment.entity;

public class UserAchievementNoDate {

    private static final long serialVersionUID = 1L;

    private int id;
    private int achievmentId;
    private Long date;
    private String userId;

    public UserAchievementNoDate() {

    }

    public UserAchievementNoDate(int id, int achievmentId, Long date, String userId) {
        this.id = id;
        this.achievmentId = achievmentId;
        this.date = date;
        this.userId = userId;
    }
    
    public UserAchievementNoDate(int achievmentId, Long date, String userId) {
        this.achievmentId = achievmentId;
        this.date = date;
        this.userId = userId;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
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

    

}
