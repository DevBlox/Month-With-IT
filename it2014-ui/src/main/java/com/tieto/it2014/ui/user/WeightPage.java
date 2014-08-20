package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.weight.WeightInputPanel;
import org.apache.wicket.Component;

/**
 * Created by mantas on 20/08/14.
 */
public class WeightPage extends BasePage {

    private static final long serialVersionUID = 1L;

    @Override
    protected Component initFullContent(String wicketId) {
        return new WeightInputPanel(wicketId);
    }

}
