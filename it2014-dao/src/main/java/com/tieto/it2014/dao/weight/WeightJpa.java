package com.tieto.it2014.dao.weight;

import com.tieto.it2014.dao.JpaEntity;
import com.tieto.it2014.domain.weight.entity.Weight;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private int id;

    @Column(name = "userId")
    private String userId;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "created")
    private Long timeStamp;

    public WeightJpa() {

    }

    public WeightJpa(Weight weight) {
        this.userId = weight.userId;
        this.weight = weight.weight;
        this.timeStamp = weight.timeStamp;
    }

    @Override
    public Weight toDomain() {
        return new Weight(weight, userId, id, timeStamp);
    }
}
