package com.tieto.it2014.dao.user;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserEntityRepositoryImpl implements UserEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> all() {
        TypedQuery<UserEntity> query = entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class);
        return (List<UserEntity>)query.getResultList();
    }

    @Override
    public UserEntity create(UserEntity user) {
        return entityManager.merge(user);
    }

    @Override
    public UserEntity update(UserEntity user) {
        return entityManager.merge(user);
    }

    @Override
    public UserEntity merge(UserEntity user) {
        return entityManager.merge(user);
    }

    @Override
    public UserEntity find(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public void delete(UserEntity user) {
        entityManager.remove(user);
    }

    @Override
    public void delete(Long id) {
        delete(find(id));
    }

}
