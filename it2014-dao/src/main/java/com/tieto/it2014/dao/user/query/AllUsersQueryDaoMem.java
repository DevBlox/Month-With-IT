package com.tieto.it2014.dao.user.query;

import com.tieto.it2014.dao.DatabaseInMemory;
import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import java.util.ArrayList;
import java.util.List;

public class AllUsersQueryDaoMem implements AllUsersQuery.Dao {
  private static final long serialVersionUID = 1L;

  @Override
  public List<User> result() {
    synchronized (DatabaseInMemory.users) {
      return new ArrayList<>(DatabaseInMemory.users);
    }
  }
}
