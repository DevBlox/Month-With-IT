package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class UserLoc extends Entity {

    private static final long serialVersionUID = 1L;

    public Long timeStamp;

    public String uName;

    public Double Latitude;
    
    public Double Longtitude;

    public UserLoc(String phoneNumber, String uName, Long timeStamp, Double Latitude, Double Longtitude) {
        super(phoneNumber);
        this.uName = uName;
        this.timeStamp = timeStamp;
        this.Latitude = Latitude;
        this.Longtitude = Longtitude;
    }


}
