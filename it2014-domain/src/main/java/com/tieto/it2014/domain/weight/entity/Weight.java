package com.tieto.it2014.domain.weight.entity;

import com.tieto.it2014.domain.weight.Entity;

public class Weight extends Entity {

    private static final long serialVersionUID = 1L;

    public Float weight;
    public String userId;
    public int id;
    public Long timeStamp;

    public Weight() {
        super(0);
    }

    public Weight(Float weight, String userId, int id, Long timeStamp) {
        super(id);
        this.id = id;
        this.userId = userId;
        this.weight = weight;
        this.timeStamp = timeStamp;
    }
}
