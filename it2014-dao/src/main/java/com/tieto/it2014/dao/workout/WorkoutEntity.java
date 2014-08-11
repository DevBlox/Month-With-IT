package com.tieto.it2014.dao.workout;

import com.tieto.it2014.domain.user.entity.UserLoc;
import com.tieto.it2014.domain.user.entity.Workout;
import javax.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "UserLoc")
public class WorkoutEntity {

    @Id
    @Column(name = "Id")
    private String Id;

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

    public WorkoutEntity() {
    }

    public WorkoutEntity(UserLoc userLoc) {
        this.phoneNumber = userLoc.id;
        this.timeStamp = userLoc.timeStamp;
        this.latitude = userLoc.latitude;
        this.longtitude = userLoc.longtitude;
        this.altitude = userLoc.altitude;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public Long getTimeStamp() { return timeStamp; }
    public Double getLatitude() { return latitude; }
    public Double getLongtitude() { return longtitude; }
    public Double getAltitude() { return altitude; }

    public UserLoc toUserLock() {
        return new UserLoc(this.phoneNumber, this.uName, this.timeStamp, this.latitude, this.longtitude, this.altitude);
    }

}
