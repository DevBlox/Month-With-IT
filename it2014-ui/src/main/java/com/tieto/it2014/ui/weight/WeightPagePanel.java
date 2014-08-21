package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.weight.detail.ChartPanel;
import com.tieto.it2014.ui.weight.detail.RandomQuote;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class WeightPagePanel extends Panel {

    private static final long serialVersionUID = 1L;
    List<Weight> data;

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
        data = weightQuery.result(imei);
        add(new WeightInputPanel("weightInput", data));
        add(new ChartPanel("weightChart", data));
        } catch (NullPointerException e) {
            setResponsePage(ErrorPage404.class);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        addQuote();
    }

    public void addQuote() {
        if (!data.isEmpty()) {
            if ((double) (Math.round(data.get(data.size() - 1).weight * 10)) / 10 >= 0) {
                add(new Label("quote", RandomQuote.getNegative()[0]));
                add(new Label("cite", RandomQuote.getNegative()[1]));
            } else {
                add(new Label("quote", RandomQuote.getPositive()[0]));
                add(new Label("cite", RandomQuote.getPositive()[1]));
            }
        } else {
            add(new Label("quote", RandomQuote.getPositive()[0]));
            add(new Label("cite", RandomQuote.getPositive()[1]));
        }
    }

}
