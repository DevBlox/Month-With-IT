package com.tieto.it2014.ui;

import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.workout.UserWorkoutPanel;
import com.tieto.it2014.ui.workout.WorkoutTopListPanel;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    private static final String title = "IRunApp";
    protected static final String HEADER_ID = "header";
    protected static final String SIDEBAR_ID = "sidebar";
    protected static final String CONTENT_ID = "content";


    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("title", new PropertyModel<String>(this, "title")));
        add(initHeader(HEADER_ID));
        add(initSidebar(SIDEBAR_ID));
        add(initContent(CONTENT_ID));
    }

    protected Component initHeader(String wicketId) {
        return new HeaderPanel(wicketId);
    }

    protected Component initSidebar(String wicketId) {
        return new Label(wicketId);
    }

    protected Component initContent(String wicketId) {
        return new Label(wicketId);
    }

}
