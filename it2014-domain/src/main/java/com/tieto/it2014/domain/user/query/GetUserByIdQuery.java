package com.tieto.it2014.domain.user.query;

import com.tieto.it2014.domain.user.entity.User;
import java.io.Serializable;

public class GetUserByIdQuery implements Serializable {
  private static final long serialVersionUID = 1L;
  private final Dao dao;

  public interface Dao extends Serializable {
    User resultOrNull(Long id);
  }

  public GetUserByIdQuery(Dao dao) {
    this.dao = dao;
  }

  public User resultOrNull(Long id) {
    return dao.resultOrNull(id);
  }
}
