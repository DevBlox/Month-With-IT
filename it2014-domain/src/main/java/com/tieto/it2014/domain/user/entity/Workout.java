package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class Workout {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String imei;
    private Long startTime;
    private Long finishTime;
    private Long distance;
    private Long duration;
    private String username;

    public Workout(Long id, String imei, Long startTime, Long finishTime, Long distance, Long duration, String username) {
        this.id = id;
        this.imei = imei;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.distance = distance;
        this.duration = duration;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    
    public String getImei() {
        return imei;
    }
    
    public Long getStartTime() {
        return startTime;
    }
    
    public Long getFinishTime() {
        return finishTime;
    }
    
    public Long getDistance() {
        return distance;
    }
    
    public Long getDuration() {
        return duration;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setImei(String imei) {
        this.imei = imei;
    }
    
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }
    
    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }
    
    public void setDistance(Long distance) {
        this.distance = distance;
    }
    
    public void setDuration(Long duration) {
        this.duration = duration;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

}
