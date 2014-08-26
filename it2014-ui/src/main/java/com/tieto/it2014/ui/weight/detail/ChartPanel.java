package com.tieto.it2014.ui.weight.detail;

import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import com.tieto.it2014.domain.Util.Util;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOverPeriod;
import com.tieto.it2014.ui.session.UserSession;
import com.tieto.it2014.ui.user.WeightPage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.tieto.it2014.domain.Util.Util.extractMonthFromTimestamp;
import static com.tieto.it2014.domain.Util.Util.extractYearFromTimestamp;
import static com.tieto.it2014.domain.weight.WeightChartType.*;

public class ChartPanel extends Panel {

    private static final long serialVersionUID = 1L;
    private static final String FIRST_QUARTER_STRING = "1";
    private static final String SECOND_QUARTER_STRING = "2";
    private static final String THIRD_QUARTER_STRING = "3";
    private static final String FOURTH_QUARTER_STRING = "4";

    @SpringBean
    private UserWeightOverPeriod weightOverPeriod;

    private List<Weight> weights;
    public static final int FIRST_QUARTER = 1;
    public static final int SECOND_QUARTER = 2;
    public static final int THIRD_QUARTER = 3;
    public static final int FORTH_QUARTER = 4;

    public static final String FIRST_QUARTER_FIRST_MONTH = "01";
    public static final String FIRST_QUARTER_LAST_MONTH = "03";
    public static final String SECOND_QUARTER_FIRST_MONTH = "04";
    public static final String SECOND_QUARTER_LAST_MONTH = "06";
    public static final String THIRD_QUARTER_FIRST_MONTH = "07";
    public static final String THIRD_QUARTER_LAST_MONTH = "09";
    public static final String FORTH_QUARTER_FIRST_MONTH = "10";
    public static final String FORTH_QUARTER_LAST_MONTH = "12";
    public static final String FIRST_DAY = "01";

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
        listQuarters = new DropDownChoice<String>(
                "days", new PropertyModel<String>(this, "selected"), getQuartersInThisYear());
        listYears = new DropDownChoice<String>(
                "days", new PropertyModel<String>(this, "selected"), getYearsList());
        options = ChartPanelOptionsProvider.getInstance().getOptions();
        chart = new Chart("chart", options);

        daysForm = new Form<Void>("dropDownForm") {
            @Override
            protected void onSubmit() {
                Long start = 0L;
                // Calendar startTemp = null;
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
                start = Util.convertToGmtLong(start);
                end = Util.convertToGmtLong(end);
                ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);

            }
        };

        monthForm = new Form<Void>("dropDownFormMonth") {
            @Override
            protected void onSubmit() {
                Long start = 0L;
                try {
                    start = createTimeStamp(getYearString(), selected, FIRST_DAY);
                } catch (ParseException ex) {
                    Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                Long end = 0L;
                end = getLastDayInMonthInCurrentYearTimestamp(selected);
                start = Util.convertToGmtLong(start);
                end = Util.convertToGmtLong(end);
                ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);

            }
        };

        yearForm = new Form<Void>("dropDownFormYear") {
            @Override
            protected void onSubmit() {
                Calendar cal = new GregorianCalendar();
                cal.set(Calendar.YEAR, Integer.parseInt(selected));
                cal.set(Calendar.MONTH, Calendar.getInstance().getActualMinimum(Calendar.MONTH));
                cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
                cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
                cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

                Long start = cal.getTimeInMillis();

                cal.set(Calendar.MONTH, Calendar.getInstance().getActualMaximum(Calendar.MONTH));
                cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
                cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMaximum(Calendar.HOUR_OF_DAY));
                cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMaximum(Calendar.MINUTE));
                cal.set(Calendar.SECOND, Calendar.getInstance().getActualMaximum(Calendar.SECOND));
                cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMaximum(Calendar.MILLISECOND));

                Long end = cal.getTimeInMillis();
                start = Util.convertToGmtLong(start);
                end = Util.convertToGmtLong(end);
                ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);

            }
        };

        quarterForm = new Form<Void>("dropDownFormQuarter") {
            @Override
            protected void onSubmit() {
                int quarter = Integer.parseInt(selected);
                Long start = 0L;
                Long end = 0L;
                switch (quarter) {
                    case FIRST_QUARTER:
                        try {
                            start = createTimeStamp(getYearString(), FIRST_QUARTER_FIRST_MONTH, FIRST_DAY);
                            end = getLastDayInMonthInCurrentYearTimestamp(FIRST_QUARTER_LAST_MONTH);

                        } catch (ParseException ex) {
                            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                        break;
                    case SECOND_QUARTER:
                        try {
                            start = createTimeStamp(getYearString(), SECOND_QUARTER_FIRST_MONTH, FIRST_DAY);
                            end = getLastDayInMonthInCurrentYearTimestamp(SECOND_QUARTER_LAST_MONTH);

                        } catch (ParseException ex) {
                            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                        break;
                    case THIRD_QUARTER:
                        try {
                            start = createTimeStamp(getYearString(), THIRD_QUARTER_FIRST_MONTH, FIRST_DAY);
                            end = getLastDayInMonthInCurrentYearTimestamp(THIRD_QUARTER_LAST_MONTH);

                        } catch (ParseException ex) {
                            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                        break;
                    case FORTH_QUARTER:
                        try {
                            start = createTimeStamp(getYearString(), FORTH_QUARTER_FIRST_MONTH, FIRST_DAY);
                            end = getLastDayInMonthInCurrentYearTimestamp(FORTH_QUARTER_LAST_MONTH);

                        } catch (ParseException ex) {
                            Logger.getLogger(ChartPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ChartPanelOptionsProvider.getInstance().getGivenTimeOptions(start, end);
                        break;
                }

            }
        };

        Form chartForm = new Form("chartForm");
        chartForm.add(initFilterButton("currentDay", BUTTON_TYPE_DAY));
        chartForm.add(initFilterButton("currentMonth", BUTTON_TYPE_MONTH));
        chartForm.add(initFilterButton("currentQuarter", BUTTON_TYPE_QUARTER));
        chartForm.add(initFilterButton("currentYear", BUTTON_TYPE_YEAR));

        ChartPanelOptionsProvider.getInstance().getOptionsType();
        add(new FeedbackPanel("feedback"));
        add(quarterForm);
        quarterForm.add(listQuarters);
        add(monthForm);
        monthForm.add(listMonths);
        add(daysForm);
        daysForm.add(listDays);
        add(yearForm);
        yearForm.add(listYears);
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
                daysForm.setVisible(true);
                monthForm.setVisible(false);
                quarterForm.setVisible(false);
                yearForm.setVisible(false);
                break;
            case BUTTON_TYPE_MONTH:
                monthForm.setVisible(true);
                daysForm.setVisible(false);
                quarterForm.setVisible(false);
                yearForm.setVisible(false);
                break;
            case BUTTON_TYPE_QUARTER:
                monthForm.setVisible(false);
                daysForm.setVisible(false);
                quarterForm.setVisible(true);
                yearForm.setVisible(false);
                break;
            case BUTTON_TYPE_YEAR:
                monthForm.setVisible(false);
                daysForm.setVisible(false);
                quarterForm.setVisible(false);
                yearForm.setVisible(true);
                break;
        }
    }

    private List<String> getDaysInThisMonth() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        Long start = cal.getTimeInMillis();

        Long end = getLastDayInMonthInCurrentYearTimestamp(String.valueOf(cal.get(Calendar.MONTH) + 1));
        List<Weight> userWeightsInMonth = weightOverPeriod.result(start, end, UserSession.get().getUser().imei, BUTTON_TYPE_MONTH);
        ArrayList<String> createdListOfDays = new ArrayList<>();
        for (Weight userWeightsInMonth1 : userWeightsInMonth) {
            createdListOfDays.add(String.valueOf(Util.extractDayFromTimestamp(userWeightsInMonth1.timeStamp)));
        }

        return createdListOfDays;
    }

    private String getMonthString() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return Integer.toString(calendar.get(Calendar.MONTH) + 1);
    }

    private String getYearString() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        return Integer.toString(calendar.get(Calendar.YEAR));
    }

    private List<String> getMonthsInThisYear() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.getInstance().getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        Long start = cal.getTimeInMillis();

        cal = Calendar.getInstance();
        Long end = getLastDayInMonthInCurrentYearTimestamp(String.valueOf(cal.get(Calendar.MONTH) + 1));
        List<Weight> userWeightsInYear = weightOverPeriod.result(start, end, UserSession.get().getUser().imei, BUTTON_TYPE_MONTH);
        ArrayList<String> createdListOfMonths = new ArrayList<>();
        for (int i = 0; i < userWeightsInYear.size(); i++) {
            if ((userWeightsInYear.size() - 1) > i) {
                if (extractMonthFromTimestamp(userWeightsInYear.get(i).timeStamp) != extractMonthFromTimestamp(userWeightsInYear.get(i + 1).timeStamp)) {
                    createdListOfMonths.add(String.valueOf(Util.extractMonthFromTimestamp(userWeightsInYear.get(i).timeStamp)));
                }
            } else {
                createdListOfMonths.add(String.valueOf(Util.extractMonthFromTimestamp(userWeightsInYear.get(i).timeStamp)));
            }
        }

        return createdListOfMonths;
    }

    private List<String> getQuartersInThisYear() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.getInstance().getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        Long start = cal.getTimeInMillis();

        cal = Calendar.getInstance();
        Long end = getLastDayInMonthInCurrentYearTimestamp(String.valueOf(cal.get(Calendar.MONTH) + 1));
        List<Weight> userWeightsInYear = weightOverPeriod.result(start, end, UserSession.get().getUser().imei, BUTTON_TYPE_MONTH);
        ArrayList<String> createdListOfQuarters = new ArrayList<>();
        for (int i = 0; i < userWeightsInYear.size(); i++) {
            if ((userWeightsInYear.size() - 1) > i) {
                int currentMonth = extractMonthFromTimestamp(userWeightsInYear.get(i).timeStamp);
                int comparedMonth = extractMonthFromTimestamp(userWeightsInYear.get(i + 1).timeStamp);
                if ((currentMonth != comparedMonth) && (currentMonth <= 3) && (comparedMonth > 3)) {
                    createdListOfQuarters.add(FIRST_QUARTER_STRING);
                } else if ((currentMonth != comparedMonth) && (currentMonth > 3) && (currentMonth <= 6) && (comparedMonth > 6)) {
                    createdListOfQuarters.add(SECOND_QUARTER_STRING);
                } else if ((currentMonth != comparedMonth) && (currentMonth <= 9) && (currentMonth > 6) && (comparedMonth > 9)) {
                    createdListOfQuarters.add(THIRD_QUARTER_STRING);
                } else if ((currentMonth != comparedMonth) && (currentMonth > 9) && (currentMonth <= 12)) {
                    createdListOfQuarters.add(FOURTH_QUARTER_STRING);
                } else if ((i == userWeightsInYear.size() - 2) && (createdListOfQuarters.isEmpty())) {
                    createdListOfQuarters.add(getMonthQuarterString(currentMonth));
                }
            }

        }
        return createdListOfQuarters;
    }

    private String getMonthQuarterString(int month) {
        if (month < 4) {
            return FIRST_QUARTER_STRING;
        } else if (month < 7) {
            return SECOND_QUARTER_STRING;
        } else if (month < 10) {
            return THIRD_QUARTER_STRING;
        } else {
            return FOURTH_QUARTER_STRING;
        }
    }

    private List<? extends String> getYearsList() {

        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, Integer.parseInt(selected));
        cal.set(Calendar.MONTH, Calendar.getInstance().getActualMinimum(Calendar.MONTH));
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));

        Long start = cal.getTimeInMillis();

        cal = Calendar.getInstance();
        Long end = cal.getTimeInMillis();
        //  Long end = getLastDayInMonthInCurrentYearTimestamp(String.valueOf(cal.get(Calendar.MONTH) + 1));
        List<Weight> userWeightsInYear = weightOverPeriod.result(start, end, UserSession.get().getUser().imei, BUTTON_TYPE_YEAR);
        ArrayList<String> createdListOfYears = new ArrayList<>();
        for (int i = 0; i < userWeightsInYear.size(); i++) {
            if ((userWeightsInYear.size() - 1) > i) {
                if (extractYearFromTimestamp(userWeightsInYear.get(i).timeStamp) != extractYearFromTimestamp(userWeightsInYear.get(i + 1).timeStamp)) {
                    createdListOfYears.add(String.valueOf(Util.extractYearFromTimestamp(userWeightsInYear.get(i).timeStamp)));
                }
            } else {
                createdListOfYears.add(String.valueOf(Util.extractYearFromTimestamp(userWeightsInYear.get(i).timeStamp)));
            }
        }

        return createdListOfYears;
    }

    private Long createTimeStamp(String year, String month, String day) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = day + "/" + month + "/" + year;
        Date date = dateFormat.parse(dateString);
        long timestamp = date.getTime();

        return timestamp;
    }

    private long getLastDayInMonthInCurrentYearTimestamp(String month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
        cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
        Date date2 = cal.getTime();
        return date2.getTime();
    }

}
