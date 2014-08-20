package com.tieto.it2014.ui.error;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class ErrorPage404 extends WebPage {

    public ErrorPage404(final PageParameters parameters) {
        add(new Label("title", "IRunApp | 404 error!"));
        add(new Label("404", "404:"));
        add(new Label("eText", "The page you requested could not be found"));

    }
}
