package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.util.Util;
import com.tieto.it2014.domain.user.Entity;

/**
 * Created by mantas on 20/08/14.
 */
public class UserStats extends Entity {

    private static final long serialVersionUID = 1L;

    public Integer topId;
    public String userName;
    public Double distance;
    public String time;
    public Double avgSpeed;

    public UserStats(String id, String userName, Double distance, Integer time, Double avgSpeed) {
        super(id);
        this.userName = userName;
        this.distance = distance;
        this.time = Util.getDurationString(time);
        this.avgSpeed = avgSpeed;
    }

    public Double getDistance() {
        return distance;
    }

}
