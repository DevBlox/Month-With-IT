package com.tieto.it2014.dao.workout;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.user.entity.UserLoc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "UserLoc")
public class WorkoutJpa implements JpaEntity<UserLoc>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Id")
    private String id;

    @Column(name = "USERNAME")
    private String uName;

    @Column(name = "imei")
    private String phoneNumber;

    @Column(name = "timeStamp")
    private Long timeStamp;

    @Column(name = "Latitude")
    private Double latitude;

    @Column(name = "Longtitude")
    private Double longtitude;

    @Column(name = "Altitutde")
    private Double altitude;

    public WorkoutJpa() {
    }

    public WorkoutJpa(UserLoc userLoc) {
        this.phoneNumber = userLoc.id;
        this.timeStamp = userLoc.timeStamp;
        this.latitude = userLoc.latitude;
        this.longtitude = userLoc.longtitude;
        this.altitude = userLoc.altitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    @Override
    public UserLoc toDomain() {
        return new UserLoc(this.phoneNumber, this.uName, this.timeStamp, this.latitude, this.longtitude, this.altitude);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
