package com.tieto.it2014.domain.weight.query;

import com.tieto.it2014.domain.weight.entity.Weight;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserWeightOverPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private Dao dao;

    public interface Dao extends Serializable {

        public List<Weight> result(long start, long finish, String imei, int type);
    }

    public List<Weight> result(long start, long finish, String imei, int type) {
        return dao.result(start, finish, imei, type);
    }
}
