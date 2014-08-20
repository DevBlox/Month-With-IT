package com.tieto.it2014.dao.weight;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.weight.entity.Weight;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mantas on 20/08/14.
 */
@Entity
@Table(name = "WEIGHT")
public class WeightJpa implements JpaEntity<Weight> {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "created")
    private Long timeStamp;

    @Override
    public Weight toDomain() {
        return new Weight(weight, userId, id, timeStamp);
    }
}
