package com.tieto.it2014.dao.workout;

import com.tieto.it2014.domain.user.entity.Workout;

import javax.persistence.*;

@Entity
@Table(name = "workout")
public class WorkoutEntity {

    @Column(name = "PhoneNumeber")
    @Id
    private String phoneNumber;

    @Column(name = "timeStamp")
    private Long timeStamp;

    @Column(name = "Latitude")
    private Double Latitude;
    
    @Column(name = "Longtitude")
    private Double Longtitude;

    public WorkoutEntity() {
    }

    public WorkoutEntity(Workout workout) {
        this.phoneNumber = workout.phoneNumber;
        this.timeStamp = workout.timeStamp;
        this.Latitude = workout.Latitude;
        this.Longtitude = workout.Longtitude;
    }

    public String getPhoneNumeber() { return phoneNumber; }
    public Long getTimeStamp() { return timeStamp; }
    public Double getLatitude() { return Latitude; }
    public Double getLongtitude() { return Longtitude; }

    public Workout toWorkout() {
        return new Workout(this.phoneNumber, this.timeStamp, this.Latitude, this.Longtitude);
    }

}
