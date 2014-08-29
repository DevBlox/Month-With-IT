package com.tieto.it2014.ui.weight.detail;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.AxisType;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.DateTimeLabelFormat;
import com.googlecode.wickedcharts.highcharts.options.DateTimeLabelFormat.DateTimeProperties;
import com.googlecode.wickedcharts.highcharts.options.ExportingOptions;
import com.googlecode.wickedcharts.highcharts.options.Function;
import com.googlecode.wickedcharts.highcharts.options.Legend;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.series.Coordinate;
import com.googlecode.wickedcharts.highcharts.options.series.CustomCoordinatesSeries;
import com.tieto.it2014.domain.util.Util;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_DAY;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_MONTH;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_QUARTER;
import static com.tieto.it2014.domain.weight.WeightChartType.BUTTON_TYPE_YEAR;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheDay;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheMonth;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheQuarter;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheYear;
import com.tieto.it2014.domain.weight.query.UserWeightOverPeriod;
import com.tieto.it2014.ui.session.UserSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ChartPanelOptionsProvider implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(ChartPanelOptionsProvider.class);

    private static ChartPanelOptionsProvider instance = null;
    private static final long serialVersionUID = 1L;
    private Options options = null;
    private int optionsType = BUTTON_TYPE_MONTH;
    private String userImei;

    @SpringBean
    private UserWeightOfTheDay weightQueryDay;

    @SpringBean
    private UserWeightOfTheMonth weightQueryMonth;

    @SpringBean
    private UserWeightOfTheQuarter weightQueryQuarter;

    @SpringBean
    private UserWeightOfTheYear weightQueryYear;

    @SpringBean
    private UserWeightOverPeriod weightOverPeriod;

    private static final String CHART_TITLE_DAY = "Weight changes in a current day";
    private static final String CHART_TITLE_MONTH = "Weight changes in a current month";
    private static final String CHART_TITLE_YEAR = "Weight changes in a current year";
    private static final String CHART_TITLE_QUATER = "Weight changes in a current quater";
    private static final String CHART_XAXIS_TITLE_DAY = "Time, h";
    private static final String CHART_XAXIS_TITLE_MONTH = "Time, days";
    private static final String CHART_XAXIS_TITLE_YEAR = "Time, months";
    private static final String CHART_XAXIS_TITLE_QUATER = "Time, days";
    private static final String CHART_YAXIS_TITLE = "Weight, kg";

    public ChartPanelOptionsProvider() {
        Injector.get().inject(this);
    }

    public static ChartPanelOptionsProvider getInstance() {
        if (instance == null) {
            instance = new ChartPanelOptionsProvider();
        }
        return instance;
    }

    public Options getDayOptions() {
        optionsType = BUTTON_TYPE_DAY;
        setUserImei(UserSession.get().getUser().imei);
        List<Weight> data = weightQueryDay.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_DAY, CHART_XAXIS_TITLE_DAY, null, null);
    }

    public Options getMonthOptions() {
        optionsType = BUTTON_TYPE_MONTH;
        setUserImei(UserSession.get().getUser().imei);
        List<Weight> data = weightQueryMonth.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_MONTH, CHART_XAXIS_TITLE_MONTH, null, null);
    }

    public Options getYearOptions() {
        optionsType = BUTTON_TYPE_YEAR;
        setUserImei(UserSession.get().getUser().imei);
        List<Weight> data = weightQueryYear.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_YEAR, CHART_XAXIS_TITLE_YEAR, null, null);
    }

    public Options getQuaterOptions() {
        optionsType = BUTTON_TYPE_QUARTER;
        setUserImei(UserSession.get().getUser().imei);
        List<Weight> data = weightQueryQuarter.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_QUATER, CHART_XAXIS_TITLE_QUATER, null, null);
    }

    public Options getGivenTimeOptions(long start, long finish) {
        List<Weight> data = weightOverPeriod.result(start, finish, UserSession.get().getUser().imei, optionsType);
        return getDefaultOptions(data, CHART_TITLE_QUATER, CHART_XAXIS_TITLE_QUATER, start, finish);
    }

    private static Float getMinWeightValue(List<Weight> data) {
        Float less = null;
        for (Weight element : data) {
            if (less == null) {
                less = element.weight;
            }

            if (element.weight < less) {
                less = element.weight;
            }
        }
        if (less == null || less < 5) {
            return (float) 0;
        } else {
            return less - 5;
        }
    }

    private List<Coordinate<String, Float>> getSeriesData(List<Weight> chartData) {
        List<Coordinate<String, Float>> data = new ArrayList<>();

        for (Weight element : chartData) {
            data.add(new Coordinate<>("Date.UTC(" + this.getUtcStringFromTimestamp(element.timeStamp) + ")", element.weight));
        }

        return data;
    }

    private String getUtcStringFromTimestamp(Long timestamp) {
        Calendar c1 = Calendar.getInstance();
        c1.setTimeInMillis(timestamp);

        c1.add(Calendar.MONTH, -1);

        return new SimpleDateFormat("yyyy, M, dd, H, m, s").format(c1.getTime());
    }

    public Options getOptions() {
        if (userImei != null) {
            if (UserSession.get().getUser().imei.equals(userImei)) {
                return options;
            } else {
                return getMonthOptions();
            }
        } else {
            if (null == options) {
                return getMonthOptions();
            } else {
                return options;
            }
        }
    }

    public Options getDefaultOptions(List<Weight> data, String chartTitle, String xAxisTitle, Long start, Long finish) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.SPLINE));

        options.setTitle(new Title(chartTitle));

        Axis xAxis = new Axis();
        xAxis.setType(AxisType.DATETIME);

        if ((null == start) && (null == finish)) {
            xAxis.setMin(getMinXDependingOnType());
            xAxis.setMax(getMaxXDependingOnType());
        } else {
            xAxis.setMin(start);
            xAxis.setMax(finish);
        }

        DateTimeLabelFormat dateTimeLabelFormat = new DateTimeLabelFormat()
                .setProperty(DateTimeProperties.MONTH, "%e. %b")
                .setProperty(DateTimeProperties.YEAR, "%b");

        xAxis.setDateTimeLabelFormats(dateTimeLabelFormat);
        xAxis.setTitle(new Title(xAxisTitle));
        options.setxAxis(xAxis);
        options.setExporting(new ExportingOptions().setEnabled(Boolean.FALSE));

        Axis yAxis = new Axis();
        yAxis.setTitle(new Title(CHART_YAXIS_TITLE));
        yAxis.setMin(getMinWeightValue(data));
        options.setyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter(new Function(
                "return '<b>Time: </b>' + Highcharts.dateFormat('%Y-%m-%d %H:%M', this.x) +'<br /><b>Weight:</b> '+ this.y +' kg';"));
        options.setTooltip(tooltip);

        options.setLegend(new Legend().setEnabled(Boolean.FALSE));

        CustomCoordinatesSeries<String, Float> series1 = new CustomCoordinatesSeries<>();
        series1.setName(null);
        series1.setData(this.getSeriesData(data));

        options.addSeries(series1);

        return options;
    }

    public int getOptionsType() {
        return optionsType;
    }

    private long getMinXDependingOnType() {
        long number = 0;
        Calendar cal = Calendar.getInstance();

        try {

            switch (this.optionsType) {
                case BUTTON_TYPE_DAY:
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));
                    cal = Util.convertToGmt(cal);
                    number = cal.getTimeInMillis();
                    break;
                case BUTTON_TYPE_MONTH:
                    cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));
                    number = cal.getTimeInMillis();
                    break;
                case BUTTON_TYPE_QUARTER:

                    cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));
                    if (Calendar.getInstance().get(Calendar.MONTH) < 3) {
                        cal.set(Calendar.MONTH, 0);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 6) {
                        cal.set(Calendar.MONTH, 3);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 9) {
                        cal.set(Calendar.MONTH, 6);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 12) {
                        cal.set(Calendar.MONTH, 9);
                    }

                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));
                    number = cal.getTimeInMillis();
                    break;
                case BUTTON_TYPE_YEAR:
                default:
                    cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
                    cal.set(Calendar.MONTH, Calendar.getInstance().getActualMinimum(Calendar.MONTH));
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMinimum(Calendar.MILLISECOND));
                    number = cal.getTimeInMillis();
                    break;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return number;
    }

    private Calendar getCalendarInstanceWithGivenValues(Calendar cal, int dayOfMonth, int hourOfDay, int minute, int second) {
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(dayOfMonth));
        cal.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().getActualMinimum(hourOfDay));
        cal.set(Calendar.MINUTE, Calendar.getInstance().getActualMinimum(minute));
        cal.set(Calendar.SECOND, Calendar.getInstance().getActualMinimum(second));

        return cal;
    }

    private long getMaxXDependingOnType() {
        long number = 0;
        Calendar cal = Calendar.getInstance();

        try {

            switch (this.optionsType) {
                case BUTTON_TYPE_DAY:
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMaximum(Calendar.MILLISECOND));
                    cal = Util.convertToGmt(cal);

                    number = cal.getTimeInMillis();

                    break;
                case BUTTON_TYPE_MONTH:
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.add(Calendar.HOUR_OF_DAY, 2);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMaximum(Calendar.MILLISECOND));

                    number = cal.getTimeInMillis();
                    break;
                case BUTTON_TYPE_QUARTER:

                    cal.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH));

                    if (Calendar.getInstance().get(Calendar.MONTH) < 3) {
                        cal.set(Calendar.MONTH, 2);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 6) {
                        cal.set(Calendar.MONTH, 5);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 9) {
                        cal.set(Calendar.MONTH, 8);
                    } else if (Calendar.getInstance().get(Calendar.MONTH) < 12) {
                        cal.set(Calendar.MONTH, 11);
                    }

                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMaximum(Calendar.MILLISECOND));

                    number = cal.getTimeInMillis();
                    break;
                case BUTTON_TYPE_YEAR:
                default:
                    cal = getCalendarInstanceWithGivenValues(cal, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND);
                    cal.set(Calendar.MILLISECOND, Calendar.getInstance().getActualMaximum(Calendar.MILLISECOND));

                    number = cal.getTimeInMillis();
                    break;
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return number;
    }

    private void setUserImei(String imei) {
        this.userImei = imei;
    }

    public void setNewOptions() {
        switch (optionsType) {
            case BUTTON_TYPE_DAY:
                getDayOptions();
                break;
            case BUTTON_TYPE_MONTH:
                getMonthOptions();
                break;
            case BUTTON_TYPE_QUARTER:
                getQuaterOptions();
                break;
            case BUTTON_TYPE_YEAR:
                getYearOptions();
                break;
            default:
                getMonthOptions();
                break;
        }
    }
}
