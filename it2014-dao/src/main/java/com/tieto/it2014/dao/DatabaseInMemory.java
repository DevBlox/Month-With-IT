package com.tieto.it2014.dao;

import com.tieto.it2014.domain.user.entity.User;
import java.util.Arrays;
import java.util.List;

public class DatabaseInMemory {
  public static final List<User> users = Arrays.asList(
      new User(1L, "Simas", 1992),
      new User(2L, "Živilė", 1994),
      new User(3L, "Tomas", 1993),
      new User(4L, "Jolanta", 1997),
      new User(5L, "Kasparas", 1989),
      new User(6L, "Darius", 1982),
      new User(7L, "Dovilė", 1995),
      new User(8L, "Romas", 1995),
      new User(9L, "Roma", 1995)
  );
}
