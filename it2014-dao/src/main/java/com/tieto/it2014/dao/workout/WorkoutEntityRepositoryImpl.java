package com.tieto.it2014.dao.workout;

import com.tieto.it2014.dao.user.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class WorkoutEntityRepositoryImpl implements WorkoutEntityRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WorkoutEntity> all() {
        TypedQuery<WorkoutEntity> query = entityManager.createQuery("SELECT u FROM WorkoutEntity u", WorkoutEntity.class);
        return (List<WorkoutEntity>)query.getResultList();
    }
    
    public List<WorkoutEntity> byUser(String imei) {
        TypedQuery<WorkoutEntity> query = entityManager.createQuery(
                "SELECT u FROM WorkoutEntity u WHERE u.imei = :imei", WorkoutEntity.class).setParameter("imei", imei);
        return (List<WorkoutEntity>)query.getResultList();
    }

    @Override
    public WorkoutEntity create(WorkoutEntity workout) {
        return entityManager.merge(workout);
    }

    @Override
    public WorkoutEntity update(WorkoutEntity workout) {
        return entityManager.merge(workout);
    }

    @Override
    public WorkoutEntity merge(WorkoutEntity workout) {
        return entityManager.merge(workout);
    }

    @Override
    public WorkoutEntity find(Long id) {
        return entityManager.find(WorkoutEntity.class, id);
    }

    @Override
    public void delete(WorkoutEntity workout) {
        entityManager.remove(workout);
    }

    @Override
    public void delete(Long id) {
        delete(find(id));
    }

}
