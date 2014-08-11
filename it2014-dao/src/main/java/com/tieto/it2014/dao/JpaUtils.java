package com.tieto.it2014.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;

public class JpaUtils {

    public static <T> T singleResultOrNull(TypedQuery<? extends JpaEntity<T>> query) {
        List<? extends JpaEntity<T>> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return toDomainEntity(resultList.get(0));
    }

    public static <T> T toDomainEntity(JpaEntity<T> jpaEntity) {
        return jpaEntity == null ? null : jpaEntity.toDomain();
    }

    public static <T> List<T> toDomainList(List<? extends JpaEntity<T>> jpaList) {
        List<T> result = new ArrayList<>();
        for (JpaEntity<T> jpaEntity : jpaList) {
            result.add(jpaEntity.toDomain());
        }
        return result;
    }

}
