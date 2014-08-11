package com.tieto.it2014.domain.user.entity;

import java.io.Serializable;
import com.tieto.it2014.domain.Util.Util;

public class Workout implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String imei;
    private String startTime;
    private String finishTime;
    private String distance;
    public int duration;

    public Workout(int id, String imei, String startTime, String finishTime, String distance, int duration) {
        this.id = id;
        this.imei = imei;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.distance = distance;
        this.duration = duration;
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
    
    public String getDistance() {
        return distance;
    }
    
    public String getDuration() {
        return Util.getDurationString(duration);
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
    
    public void setDistance(String distance) {
        this.distance = distance;
    }

}
