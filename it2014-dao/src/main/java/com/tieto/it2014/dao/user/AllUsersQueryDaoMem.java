package com.tieto.it2014.dao.user;

import com.tieto.it2014.domain.user.entity.User;
import com.tieto.it2014.domain.user.query.AllUsersQuery;
import com.tieto.it2014.dao.DatabaseInMemory;
import java.util.List;

public class AllUsersQueryDaoMem implements AllUsersQuery.Dao {
  @Override
  public List<User> result() {
    return DatabaseInMemory.users;
  }
}
