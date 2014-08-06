package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.user.UserEntityRepository;
import com.tieto.it2014.dao.user.UserEntity;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetUserByIdQueryDaoMem implements GetUserByIdQuery.Dao {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserEntityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public User resultOrNull(Long id) {
        UserEntity entity = repository.find(id);
        return entity != null ? entity.toUser() : null;
    }

}
