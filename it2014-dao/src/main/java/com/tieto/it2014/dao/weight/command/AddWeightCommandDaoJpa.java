package com.tieto.it2014.dao.weight.command;

import com.tieto.it2014.dao.weight.WeightJpa;
import com.tieto.it2014.domain.weight.command.AddWeightCommand;
import com.tieto.it2014.domain.weight.entity.Weight;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mantas on 20/08/14.
 */
@Component
public class AddWeightCommandDaoJpa implements AddWeightCommand.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void execute(Weight weight) {
        em.merge(new WeightJpa(weight));
    }
}
