package com.tieto.it2014.ui;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  public HomePage() {
    add(new Label("message", "Hello World :)"));
  }
}
