package com.tieto.it2014.domain.user.entity;

import com.tieto.it2014.domain.user.Entity;

public class User extends Entity {
  private static final long serialVersionUID = 1L;
  public String name;
  public Integer yearOfBirth;

  public User(Long id, String name, Integer yearOfBirth) {
    super(id);
    this.name = name;
    this.yearOfBirth = yearOfBirth;
  }

  public User createCopy() {
    return new User(id, name, yearOfBirth);
  }
}
