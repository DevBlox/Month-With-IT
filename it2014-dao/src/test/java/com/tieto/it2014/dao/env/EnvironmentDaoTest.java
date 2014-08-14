package com.tieto.it2014.dao.env;

import org.junit.Test;

public class EnvironmentDaoTest {
  @Test
  public void reads_environment_comment() {
    System.out.println("Environment [" + EnvironmentDao.getComment() + "]");
  }
}
