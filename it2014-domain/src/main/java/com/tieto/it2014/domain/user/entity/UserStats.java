package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;
import com.tieto.it2014.domain.util.Util;

/**
 * Created by mantas on 20/08/14.
 */
public class UserStats extends Entity {

    private static final long serialVersionUID = 1L;

    private Integer topId;
    private String userName;
    private Double distance;
    private String time;
    private Double avgSpeed;

    public UserStats(String id, String userName, Double distance, Integer time, Double avgSpeed) {
        super(id);
        this.userName = userName;
        this.distance = distance;
        this.time = Util.getDurationString(time);
        this.avgSpeed = avgSpeed;
    }

    /**
     * @return the topId
     */
    public Integer getTopId() {
        return topId;
    }

    /**
     * @param topId the topId to set
     */
    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(Double distance) {
        this.distance = distance;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the avgSpeed
     */
    public Double getAvgSpeed() {
        return avgSpeed;
    }

    /**
     * @param avgSpeed the avgSpeed to set
     */
    public void setAvgSpeed(Double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    /**
     * @return the distance
     */
    public Double getDistance() {
        return distance;
    }

}
