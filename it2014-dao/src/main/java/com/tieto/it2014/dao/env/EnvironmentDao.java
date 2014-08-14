package com.tieto.it2014.dao.env;

import java.io.InputStreamReader;
import java.util.Properties;

public class EnvironmentDao {
  public static String getComment() {
    Properties properties = new Properties();
    try {
      properties.load(new InputStreamReader(
          EnvironmentDao.class.getResourceAsStream("/environment.properties"),
          "utf-8"));
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }
    return properties.getProperty("it2014.env.comment");
  }
}
