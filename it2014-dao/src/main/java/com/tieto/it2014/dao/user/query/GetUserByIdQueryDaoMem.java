package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.DatabaseInMemory;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.GetUserByIdQuery;
import java.util.Objects;

public class GetUserByIdQueryDaoMem implements GetUserByIdQuery.Dao {
  private static final long serialVersionUID = 1L;

  @Override
  public User resultOrNull(Long id) {
    synchronized (DatabaseInMemory.users) {
      for (User user : DatabaseInMemory.users) {
        if (Objects.equals(user.id, id)) {
          return user;
        }
      }
      return null;
    }
  }
}
