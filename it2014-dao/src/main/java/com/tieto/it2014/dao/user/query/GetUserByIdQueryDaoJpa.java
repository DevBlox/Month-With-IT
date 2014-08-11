package com.tieto.it2014.dao.user.query;

import static com.tieto.it2014.dao.JpaUtils.toDomainEntity;
import com.tieto.it2014.dao.user.UserJpa;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetUserByIdQueryDaoJpa implements GetUserByIdQuery.Dao {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public User resultOrNull(Long id) {
        return toDomainEntity(em.find(UserJpa.class, id));
    }

}
