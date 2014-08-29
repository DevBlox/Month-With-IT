package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class UserLoc extends Entity {

    private static final long serialVersionUID = 1L;

    private Long timeStamp;

    private String uName;

    private Double latitude;

    private Double longtitude;

    private Double altitude;

    public UserLoc(String phoneNumber, String uName, Long timeStamp, Double latitude, Double longtitude, Double altitude) {
        super(phoneNumber);
        this.uName = uName;
        this.timeStamp = timeStamp;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.altitude = altitude;
    }

    /**
     * @return the timeStamp
     */
    public Long getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the uName
     */
    public String getuName() {
        return uName;
    }

    /**
     * @param uName the uName to set
     */
    public void setuName(String uName) {
        this.uName = uName;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longtitude
     */
    public Double getLongtitude() {
        return longtitude;
    }

    /**
     * @param longtitude the longtitude to set
     */
    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    /**
     * @return the altitude
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

}
