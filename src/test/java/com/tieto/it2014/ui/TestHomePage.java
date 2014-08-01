package com.tieto.it2014.ui;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class TestHomePage {
  private final WicketTester tester = new WicketTester(new WicketApplication());

  @Test
  public void renders_successfully() {
    tester.startPage(HomePage.class);
    tester.assertRenderedPage(HomePage.class);
  }
}
