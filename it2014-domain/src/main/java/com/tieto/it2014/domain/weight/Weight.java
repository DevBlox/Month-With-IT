package com.tieto.it2014.domain.weight;

public class Weight {

    private static final long serialVersionUID = 1L;

    public Float weight;
    public String userId;
    public int id;

    public Weight() {
    }

    public Weight(Float weight, String userId, int id) {
        this.id = id;
        this.userId = userId;
        this.weight = weight;
    }
}
