package com.tieto.it2014.domain.user.query;

import com.tieto.it2014.domain.user.entity.User;
import java.util.List;

public class AllUsersQuery {
  private final Dao dao;

  public interface Dao {
    List<User> result();
  }

  public AllUsersQuery(Dao dao) {
    this.dao = dao;
  }

  public List<User> result() {
    return dao.result();
  }
}
