package com.tieto.it2014.dao;

public interface JpaEntity<T> {

    T toDomain();
}
