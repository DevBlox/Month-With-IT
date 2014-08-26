package com.tieto.it2014.ui;

import com.tieto.it2014.ui.header.HeaderPanel;
import com.tieto.it2014.ui.utils.NiceFeedbackPanel;
import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

import static com.tieto.it2014.ui.utils.UIUtils.ajaxReady;

public abstract class BasePage extends WebPage {

    private static final long serialVersionUID = 1L;
    protected static final String HEADER_ID = "header";
    protected static final String SIDEBAR_ID = "sidebar";
    protected static final String CONTENT_ID = "content";
    protected static final String CONTENT_FULL_ID = "content-full";

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("title", "IRunApp"));
        add(ajaxReady(new NiceFeedbackPanel("feedback", new IFeedbackMessageFilter() {
            @Override
            public boolean accept(FeedbackMessage message) {
                return !message.isError();
            }
        })));

        add(initHeader(HEADER_ID));
        add(initSidebar(SIDEBAR_ID));
        add(initContent(CONTENT_ID));
        add(initFullContent(CONTENT_FULL_ID));
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
