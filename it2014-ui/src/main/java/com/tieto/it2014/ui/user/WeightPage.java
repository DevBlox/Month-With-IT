package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.weight.WeightPagePanel;
import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by mantas on 20/08/14.
 */
public class WeightPage extends BasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected Component initFullContent(String wicketId) {
        return new WeightPagePanel(wicketId);
    }

}
