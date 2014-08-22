package com.tieto.it2014.ui.weight.detail;

import static com.google.common.collect.ComparisonChain.start;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_DAY;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_MONTH;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_QUARTER;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_YEAR;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.ui.user.WeightPage;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class ChartPanel extends Panel {

    private static final long serialVersionUID = 1L;

    private List<Weight> weights;

//    private Integer[] intArrayOfDays = new Integer[]{0, 1, 2, 3};
//    private ArrayList<Integer> intListOfDays = new ArrayList<Integer>(Arrays.asList(intArrayOfDays));
//
//    private static final List<String> DAYS = Arrays.asList(new String[]{
//        "1", "2", "3"});
//    private List<String> daysInMonth = getDaysInThisMonth();
    private String selected = "1";
    private DropDownChoice<String> listDays;
    private DropDownChoice<String> listMonths;
    private DropDownChoice<String> listYears;
    private DropDownChoice<String> listQuarters;
    private Form<?> daysForm;
    private Form<?> monthForm;
    private Form<?> yearForm;
    private Form<?> quarterForm;

    private Options options = new Options();
    private Chart chart;

    public ChartPanel(String id, List<Weight> weights) {
        this(id, new Options());
        this.weights = weights;
    }

    public ChartPanel(String id, Options options) {
        super(id);
        this.options = options;
    }

    public Options getChartOptions() {
        return this.options;
    }

    public void setChartOptions(Options options) {
        this.options = options;
    }

    private Component initFilterButton(String wicketId, int type) {
        AjaxSubmitLink button;

        if (BUTTON_TYPE_DAY == type) {
            button = new AjaxSubmitLink(wicketId) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    options = ChartPanelOptionsProvider.getInstance().getDayOptions();
                    chart.setOptions(options);
                    setResponsePage(WeightPage.class);
                }
            };
        } else if (BUTTON_TYPE_MONTH == type) {
            button = new AjaxSubmitLink(wicketId) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    options = ChartPanelOptionsProvider.getInstance().getMonthOptions();
                    chart.setOptions(options);
                    setResponsePage(WeightPage.class);
                }
            };
        } else if (BUTTON_TYPE_QUARTER == type) {
            button = new AjaxSubmitLink(wicketId) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    options = ChartPanelOptionsProvider.getInstance().getQuaterOptions();
                    chart.setOptions(options);
                    setResponsePage(WeightPage.class);
                }
            };
        } else {
            button = new AjaxSubmitLink(wicketId) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                    options = ChartPanelOptionsProvider.getInstance().getYearOptions();
                    chart.setOptions(options);
                    setResponsePage(WeightPage.class);
                }
            };
        }

        return button;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        listDays = new DropDownChoice<String>(
                "days", new PropertyModel<String>(this, "selected"), getDaysInThisMonth());
        listMonths = new DropDownChoice<String>(
                "days", new PropertyModel<String>(this, "selected"), getMonthsInThisYear());
//        listYears = new DropDownChoice<String>(
//                "days", new PropertyModel<String>(this, "selected"), getYe());
        listQuarters = new DropDownChoice<String>(
                "days", new PropertyModel<String>(this, "selected"), getQuartersInThisYear());
        options = ChartPanelOptionsProvider.getInstance().getOptions();
        chart = new Chart("chart", options);

//        DropDownChoice<String> listDays = new DropDownChoice<String>(
//                "days", new PropertyModel<String>(this, "selected"), getDaysInThisMonth());
        daysForm = new Form<Void>("dropDownForm") {
            @Override
            protected void onSubmit() {
                Long start = 0L;
                try {
                    start = createTimeStamp(getYearString(), getMonthString(), selected);
                } catch (ParseException ex) {
                    Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                int endInt = Integer.parseInt(selected) + 1;
                String endString = Integer.toString(endInt);
                Long end = 0L;
                try {
                    end = createTimeStamp(getYearString(), getMonthString(), endString);
                } catch (ParseException ex) {
                    Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                System.out.println("pradžia: " + start + " pabaiga: " + end);
               // info("Jūs pasirinkote : " + selected);

            }
        };

        monthForm = new Form<Void>("dropDownFormMonth") {
            @Override
            protected void onSubmit() {
                Long start = 0L;
                start = getLastDayInMonthInCurrentYearTimestamp(selected);
                Long end = 0L;
                try {
                    end = createTimeStamp(getYearString(), selected, "01");
                } catch (ParseException ex) {
                    Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

                ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                System.out.println("pradžia: " + start + " pabaiga: " + end);

            //    info("Jūs pasirinkote : " + selected);

            }
        };

        quarterForm = new Form<Void>("dropDownFormQuarter") {
            @Override
            protected void onSubmit() {

                info("Jūs pasirinkote : " + selected);

            }
        };

        Form chartForm = new Form("chartForm");
        chartForm.add(initFilterButton("currentDay", BUTTON_TYPE_DAY));
        chartForm.add(initFilterButton("currentMonth", BUTTON_TYPE_MONTH));
        chartForm.add(initFilterButton("currentQuarter", BUTTON_TYPE_QUARTER));
        chartForm.add(initFilterButton("currentYear", BUTTON_TYPE_YEAR));

//        Form dayForm = new Form("dayForm");
//        Form monthForm = new Form("monthForm");
//        Form quarterForm = new Form("quarterForm");
//        Form yearForm = new Form("yearForm");
//        add(chartForm);
//        add(dayForm);
//        add(monthForm);
//        add(quarterForm);
//        add(yearForm);
        // int kokia forma vaizduoti
        ChartPanelOptionsProvider.getInstance().getOptionsType();
        add(new FeedbackPanel("feedback"));
        add(quarterForm);
        quarterForm.add(listQuarters);
        add(monthForm);
        monthForm.add(listMonths);
        add(daysForm);
        daysForm.add(listDays);
        add(chart);
        add(chartForm);

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        options = ChartPanelOptionsProvider.getInstance().getOptions();
        chart.setOptions(options);
        hideForms(ChartPanelOptionsProvider.getInstance().getOptionsType());
    }

    private void hideForms(int type) {
        switch (type) {
            case BUTTON_TYPE_DAY:

        }
    }

}
