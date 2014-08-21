package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.weight.detail.ChartPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class WeightPagePanel extends Panel {

    private static final long serialVersionUID = 1L;

    public WeightPagePanel(String id) {
        super(id);
    }

    @SpringBean
    private WeightQuery weightQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        try {
        String imei = UserSession.get().getUser().imei;
        List<Weight> data = weightQuery.result(imei);
        add(new WeightInputPanel("weightInput", data));
        add(new ChartPanel("weightChart", data));
        } catch (NullPointerException e) {
            setResponsePage(ErrorPage404.class);
        }
    }

}
