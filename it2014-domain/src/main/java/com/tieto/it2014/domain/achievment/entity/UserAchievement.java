package com.tieto.it2014.domain.achievment.entity;

import java.io.Serializable;

public class UserAchievement implements Serializable {

    private static final long serialVersionUID = 1L;

    private int achievmentId;
    private String name;
    private String description;
    private String completeMessage;
    private String imei;
    private Long date;
    private Boolean isNew;
    private Boolean isSeen;

    public UserAchievement() {

    }

    public UserAchievement(int achievmentId, String name, String description, String completeMessage, String imei, Long date, Boolean isNew, Boolean isSeen) {
        this.achievmentId = achievmentId;
        this.name = name;
        this.description = description;
        this.completeMessage = completeMessage;
        this.imei = imei;
        this.date = date;
        this.isNew = isNew;
        this.isSeen = isSeen;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the completeMessage
     */
    public String getCompleteMessage() {
        return completeMessage;
    }

    /**
     * @param completeMessage the completeMessage to set
     */
    public void setCompleteMessage(String completeMessage) {
        this.completeMessage = completeMessage;
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
