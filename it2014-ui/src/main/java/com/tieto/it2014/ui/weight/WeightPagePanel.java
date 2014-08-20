package com.tieto.it2014.ui.weight;

import com.tieto.it2014.ui.weight.detail.ChartPanel;
import org.apache.wicket.markup.html.panel.Panel;

public class WeightPagePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public WeightPagePanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new WeightInputPanel("weightInput"));
        add(new ChartPanel("weightChart"));
    }

}
