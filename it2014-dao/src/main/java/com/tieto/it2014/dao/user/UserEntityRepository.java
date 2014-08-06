package com.tieto.it2014.dao.user;

import java.util.List;

public interface UserEntityRepository {

    List<UserEntity> all();
    UserEntity create(UserEntity user);
    UserEntity update(UserEntity user);
    UserEntity merge(UserEntity user);
    UserEntity find(Long id);
    void delete(UserEntity user);
    void delete(Long id);

}
