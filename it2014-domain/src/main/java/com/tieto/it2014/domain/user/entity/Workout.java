package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class Workout extends Entity {

    private static final long serialVersionUID = 1L;

    public String phoneNumber;

    public Long timeStamp;

    public Double Latitude;
    
    public Double Longtitude;


    public Workout(Long timeStamp, Double Latitude, Double Longtitude) {
        this(null, timeStamp, Latitude, Longtitude);
    }

    public Workout(String phoneNumber, Long timeStamp, Double Latitude, Double Longtitude) {
        super(Long.parseLong(phoneNumber));
        this.timeStamp = timeStamp;
        this.Latitude = Latitude;
        this.Longtitude = Longtitude;
    }


}
