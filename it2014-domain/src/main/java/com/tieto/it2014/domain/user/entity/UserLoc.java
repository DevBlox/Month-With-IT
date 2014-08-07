package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class UserLoc extends Entity {

    private static final long serialVersionUID = 1L;

    public Long timeStamp;

    public Double Latitude;
    
    public Double Longtitude;

    public UserLoc(String phoneNumber, Long timeStamp, Double Latitude, Double Longtitude) {
        super(phoneNumber);
        this.timeStamp = timeStamp;
        this.Latitude = Latitude;
        this.Longtitude = Longtitude;
    }


}
