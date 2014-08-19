package com.tieto.it2014.ui.utils;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class NiceFeedbackPanel extends FeedbackPanel {

    private static final long serialVersionUID = 1L;

    public NiceFeedbackPanel(final String id, IFeedbackMessageFilter filter) {
        super(id, filter);
    }

    public NiceFeedbackPanel(final String id) {
        super(id);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVisible(anyMessage());
    }
}
