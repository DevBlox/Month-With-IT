package com.tieto.it2014.domain.weight.command;

import com.tieto.it2014.domain.weight.entity.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class AddWeightCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {
        void execute(Weight weight);
    }

    public void execute(Weight weight) {
        dao.execute(weight);
    }

}
