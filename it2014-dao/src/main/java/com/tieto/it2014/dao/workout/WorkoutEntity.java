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

    @Column(name = "imei")
    private String phoneNumber;

    @Column(name = "timeStamp")
    private Long timeStamp;

    @Column(name = "Latitude")
    private Double Latitude;
    
    @Column(name = "Longtitude")
    private Double Longtitude;

    public WorkoutEntity() {
    }

    public WorkoutEntity(UserLoc userLoc) {
        this.phoneNumber = userLoc.id;
        this.timeStamp = userLoc.timeStamp;
        this.Latitude = userLoc.Latitude;
        this.Longtitude = userLoc.Longtitude;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public Long getTimeStamp() { return timeStamp; }
    public Double getLatitude() { return Latitude; }
    public Double getLongtitude() { return Longtitude; }

    public UserLoc toUserLock() {
        return new UserLoc(this.phoneNumber, this.timeStamp, this.Latitude, this.Longtitude);
    }

}
