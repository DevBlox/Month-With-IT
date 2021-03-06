package com.tieto.it2014.domain.user.entity;

import com.google.common.collect.Lists;
import com.tieto.it2014.domain.util.Util;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class Workout implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<UserLoc> locs;

    private int id;

    public Workout(UserLoc loc) {
        this.locs = Lists.newArrayList(loc);
    }

    public void addLoc(UserLoc loc) {
        locs.add(loc);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public UserLoc getLastLoc() {
        return locs.get(locs.size() - 1);
    }

    public String getImei() {
        return locs.get(0).id;
    }

    public String getStartTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(locs.get(0).getTimeStamp());
    }

    public Long getStartTimeTimestamp() {
        return locs.get(0).getTimeStamp();
    }

    public String getFinishTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(locs.get(locs.size() - 1).getTimeStamp());
    }

    public List<UserLoc> getLocs() {
        return this.locs;
    }

    public String getDistance() {
        double d = 0;
        if (locs.size() > 1) {
            UserLoc previous = null;
            for (UserLoc l : locs) {
                if (previous != null) {
                    d += Util.calculateDistance(
                            previous.getLatitude(),
                            previous.getLongtitude(),
                            previous.getAltitude(),
                            l.getLatitude(),
                            l.getLongtitude(),
                            l.getAltitude());
                }
                previous = l;
            }
        }
        return Util.formatDoubleToString(d);
    }

    public double getDistanceDouble() {
        double d = 0;
        if (locs.size() > 1) {
            UserLoc previous = null;
            for (UserLoc l : locs) {
                if (previous != null) {
                    d += Util.calculateDistance(
                            previous.getLatitude(),
                            previous.getLongtitude(),
                            previous.getAltitude(),
                            l.getLatitude(),
                            l.getLongtitude(),
                            l.getAltitude());
                }
                previous = l;
            }
        }
        return d;
    }

    public String getDuration() {
        int d = 0;
        if (locs.size() > 1) {
            UserLoc previous = null;
            for (UserLoc l : locs) {
                if (previous != null) {
                    d += Util.calculateDuration(previous.getTimeStamp(), l.getTimeStamp());
                }
                previous = l;
            }
        }
        return Util.getDurationString(d);
    }

    public int getDurationInt() {
        int d = 0;
        if (locs.size() > 1) {
            UserLoc previous = null;
            for (UserLoc l : locs) {
                if (previous != null) {
                    d += Util.calculateDuration(previous.getTimeStamp(), l.getTimeStamp());
                }
                previous = l;
            }
        }
        return d;
    }

}
