package com.tieto.it2014.ui.user;

import org.apache.wicket.markup.html.panel.Panel;

public class ProfilePagePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public ProfilePagePanel(String id) {
        super(id);
        add(new UpdateProfilePanel("updatePanel"));
    }

}
