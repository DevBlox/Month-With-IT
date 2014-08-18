package com.tieto.it2014.ui;

import com.tieto.it2014.ui.header.HeaderPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.http.WebResponse;

public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    private static final String title = "IRunApp";
    protected static final String HEADER_ID = "header";
    protected static final String SIDEBAR_ID = "sidebar";
    protected static final String CONTENT_ID = "content";
    protected static final String CONTENT_FULL_ID = "content-full";

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("title", new PropertyModel<String>(this, "title")));
        add(initHeader(HEADER_ID));
        add(initSidebar(SIDEBAR_ID));
        add(initContent(CONTENT_ID));
        add(initFullContent(CONTENT_FULL_ID));
    }

    @Override
    protected void setHeaders(WebResponse response) {
        super.setHeaders(response);
        response.disableCaching();
        response.setHeader("X-Frame-Options","deny"); // Avoid  IFRFAMES
        response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0");
        response.setHeader("Expires","0");
        response.setHeader("Pragma", "no-cache");
    }

    protected Component initContent(String wicketId) {
        return empty(wicketId);
    }

    protected Component initFullContent(String wicketId) {
        return empty(wicketId);
    }

    protected Component initSidebar(String wicketId) {
        return empty(wicketId);
    }

    protected Component initHeader(String wicketId) {
        return new HeaderPanel(wicketId);
    }

    protected Component empty(String id) {
        return new Label(id);
    }
}
