package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class UserLoc extends Entity {

    private static final long serialVersionUID = 1L;

    public Long timeStamp;

    public String uName;

    public Double latitude;
    
    public Double longtitude;

    public Double altitude;

    public UserLoc(String phoneNumber, String uName, Long timeStamp, Double latitude, Double longtitude, Double altitude) {
        super(phoneNumber);
        this.uName = uName;
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.altitude = altitude;
    }


}
