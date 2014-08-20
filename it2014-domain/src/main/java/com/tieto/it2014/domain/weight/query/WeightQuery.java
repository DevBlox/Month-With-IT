package com.tieto.it2014.domain.weight.query;
import com.tieto.it2014.domain.weight.entity.Weight;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mantas on 20/08/14.
 */
public class WeightQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        List<Weight> result(String imei);
    }
}
