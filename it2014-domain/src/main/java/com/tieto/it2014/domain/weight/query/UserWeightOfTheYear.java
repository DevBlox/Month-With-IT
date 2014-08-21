package com.tieto.it2014.domain.weight.query;

import com.tieto.it2014.domain.weight.entity.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mantas on 21/08/14.
 */
@Component
public class UserWeightOfTheYear implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        List<Weight> result(String imei);
    }

    public List<Weight> result(String imei) {
        return dao.result(imei);
    }
}
