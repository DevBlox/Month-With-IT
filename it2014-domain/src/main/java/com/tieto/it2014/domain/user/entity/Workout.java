package com.tieto.it2014.domain.user.entity;

import java.io.Serializable;

public class Workout implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String imei;
    private String startTime;
    private String finishTime;
    private Double distance;
    private String duration;
    private String username;

    public Workout(int id, String imei, String startTime, String finishTime, Double distance, String duration, String username) {
        this.id = id;
        this.imei = imei;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.distance = distance;
        this.duration = duration;
        this.username = username;
    }

    public int getId() {
        return id;
    }
    
    public String getImei() {
        return imei;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public String getFinishTime() {
        return finishTime;
    }
    
    public Double getDistance() {
        return distance;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setImei(String imei) {
        this.imei = imei;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
    
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

}
