package com.tieto.it2014.dao.weight.query;

import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class AllWeightQueryDaoJpa implements WeightQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Weight> result(String imei) {
        List<Weight> lw = new ArrayList<>();
        lw.add(new Weight((float) 50.5, "355866055632819", 1, 1408531889L));
        lw.add(new Weight((float) 60.5, "355866055632819", 2, 1408631989L));
        lw.add(new Weight((float) 70.5, "355866055632819", 3, 1408732089L));
        lw.add(new Weight((float) 60.5, "355866055632819", 4, 1408832189L));
        lw.add(new Weight((float) 50.5, "355866055632819", 5, 1408932289L));

        return lw;
    }
}
