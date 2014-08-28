package com.tieto.it2014.domain.achievment.entity;

public class UserAchievementNoDate {

    private static final long serialVersionUID = 1L;

    private int id;
    private int achievmentId;
    private Long date;
    private String userId;
    private Boolean isNew;
    private Boolean isSeen;

    public UserAchievementNoDate() {

    }

    public UserAchievementNoDate(int id, int achievmentId, Long date, String userId, Boolean isNew, Boolean isSeen) {
        this.id = id;
        this.achievmentId = achievmentId;
        this.date = date;
        this.userId = userId;
        this.isNew = isNew;
        this.isSeen = isSeen;
    }
    
    public UserAchievementNoDate(int achievmentId, Long date, String userId, Boolean isNew, Boolean isSeen) {
        this.achievmentId = achievmentId;
        this.date = date;
        this.userId = userId;
        this.isNew = isNew;
        this.isSeen = isSeen;
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

    

}
