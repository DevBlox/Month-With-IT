package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.top.TopPanel;
import org.apache.wicket.Component;

/**
 * Created by mantas on 20/08/14.
 */
public class TopPage extends BasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();
    }

    @Override
    protected Component initFullContent(String wicketId) {
        return new TopPanel(wicketId);
    }
}
