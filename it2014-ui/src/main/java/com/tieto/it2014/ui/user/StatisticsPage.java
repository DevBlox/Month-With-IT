package com.tieto.it2014.ui.user;

import com.tieto.it2014.ui.BasePage;
import com.tieto.it2014.ui.statistics.StatsPanel;
import org.apache.wicket.Component;

/**
 * Created by mantas on 20/08/14.
 */
public class StatisticsPage extends BasePage {

    @Override
    protected Component initFullContent(String wicketId) {
        return new StatsPanel(wicketId);
    }
}
