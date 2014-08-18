package com.tieto.it2014.ui.utils;

import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class NiceFeedbackPanel extends FeedbackPanel {

    private static final long serialVersionUID = 1L;

    public NiceFeedbackPanel(String id) {
        super(id);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyMessage());
    }
}
