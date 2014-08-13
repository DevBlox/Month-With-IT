package com.tieto.it2014.ui;

import com.tieto.it2014.ui.header.HeaderPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    private static final String title = "IRunApp";

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("title", new PropertyModel<String>(this, "title")));
        
        if (get("header") == null) {
            this.setHeader("header");
        }
    }

    protected void setHeader(String componentName){
        add(new HeaderPanel(componentName));
    }

}
