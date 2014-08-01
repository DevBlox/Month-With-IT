package com.tieto.it2014.ui;

import java.io.Serializable;

public class User implements Serializable {
  private static final long serialVersionUID = 1L;
  public final Long id;
  public final String name;
  public final int yearOfBirth;

  public User(Long id, String name, int yearOfBirth) {
    this.id = id;
    this.name = name;
    this.yearOfBirth = yearOfBirth;
  }
}
