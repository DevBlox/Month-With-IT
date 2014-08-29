package com.tieto.it2014.ui.weight;

import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import com.tieto.it2014.ui.error.ErrorPage404;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.weight.detail.ChartPanel;
import com.tieto.it2014.ui.weight.detail.RandomQuote;
import org.apache.log4j.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.List;

public class WeightPagePanel extends Panel {

    private static final long serialVersionUID = 1L;
    private List<Weight> data;
    private String imei;

    private static final Logger logger = Logger.getLogger(WeightPagePanel.class);

    public WeightPagePanel(String id) {
        super(id);
    }

    @SpringBean
    private WeightQuery weightQuery;

    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("quote", ""));
        add(new Label("cite", ""));
        try {
            imei = UserSession.get().getUser().imei;
            data = weightQuery.result(imei);
            add(new WeightInputPanel("weightInput", data));
            add(new ChartPanel("weightChart", data));
        } catch (NullPointerException e) {
            logger.error(e.getMessage());
            setResponsePage(ErrorPage404.class);
        }
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        data = weightQuery.result(imei);
        addQuote();
    }

    public void addQuote() {
        this.remove("quote");
        this.remove("cite");
        String[] pos = RandomQuote.getPositive();
        String[] neg = RandomQuote.getNegative();
        if (!data.isEmpty()) {
            if (data.get(data.size() - 1).weight - data.get(0).weight > 0) {
                add(new Label("quote", neg[0]));
                add(new Label("cite", neg[1]));
            } else {
                add(new Label("quote", pos[0]));
                add(new Label("cite", pos[1]));
            }
        } else {
            add(new Label("quote", pos[0]));
            add(new Label("cite", pos[1]));
        }
    }

}
