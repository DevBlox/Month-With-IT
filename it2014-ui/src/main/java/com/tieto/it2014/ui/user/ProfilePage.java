package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BasePage;
import org.apache.wicket.Component;

/**
 * Created by mantas on 20/08/14.
 */
public class ProfilePage extends BasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected Component initFullContent(String wicketId) {
        return new ProfilePagePanel(wicketId);
    }

}
