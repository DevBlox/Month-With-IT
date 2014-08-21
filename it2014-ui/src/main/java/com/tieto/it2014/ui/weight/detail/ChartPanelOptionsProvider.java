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
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheDay;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheMonth;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheQuarter;
import com.tieto.it2014.domain.weight.query.UserWeightOfTheYear;
import com.tieto.it2014.ui.session.UserSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ChartPanelOptionsProvider implements Serializable {

    private static ChartPanelOptionsProvider instance = null;
    private static final long serialVersionUID = 1L;
    private Options options = null;
    private int optionsType = ChartPanel.BUTTON_TYPE_MONTH;

    @SpringBean
    private UserWeightOfTheDay weightQueryDay;

    @SpringBean
    private UserWeightOfTheMonth weightQueryMonth;

    @SpringBean
    private UserWeightOfTheQuarter weightQueryQuarter;

    @SpringBean
    private UserWeightOfTheYear weightQueryYear;

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
        optionsType = ChartPanel.BUTTON_TYPE_DAY;
        List<Weight> data = weightQueryDay.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_DAY, CHART_XAXIS_TITLE_DAY);
    }

    public Options getMonthOptions() {
        optionsType = ChartPanel.BUTTON_TYPE_MONTH;
        List<Weight> data = weightQueryMonth.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_MONTH, CHART_XAXIS_TITLE_MONTH);
    }

    public Options getYearOptions() {
        optionsType = ChartPanel.BUTTON_TYPE_YEAR;
        List<Weight> data = weightQueryYear.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_YEAR, CHART_XAXIS_TITLE_YEAR);
    }

    public Options getQuaterOptions() {
        optionsType = ChartPanel.BUTTON_TYPE_QUARTER;
        List<Weight> data = weightQueryQuarter.result(UserSession.get().getUser().imei);
        return getDefaultOptions(data, CHART_TITLE_QUATER, CHART_XAXIS_TITLE_QUATER);
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
        List<Coordinate<String, Float>> data = new ArrayList<Coordinate<String, Float>>();

        for (Weight element : chartData) {
            data.add(new Coordinate<String, Float>("Date.UTC(" + this.getUtcStringFromTimestamp(element.timeStamp) + ")", element.weight));
        }

        return data;
    }

    private String getUtcStringFromTimestamp(Long timestamp) {
        return new SimpleDateFormat("yyyy, M, dd, H, m, s").format(new java.sql.Timestamp(timestamp));
    }

    public Options getOptions() {
        if (null == options) {
            return getMonthOptions();
        } else {
            return options;
        }
    }

    public Options getDefaultOptions(List<Weight> data, String chartTitle, String xAxisTitle) {
        options = new Options();
        options.setChartOptions(new ChartOptions().setType(SeriesType.SPLINE));

        options.setTitle(new Title(chartTitle));

        Axis xAxis = new Axis();
        xAxis.setType(AxisType.DATETIME);

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
                "return '<b>'+ this.series.name +'</b><br/>'+Highcharts.dateFormat('%H:%M', this.x) +': '+ this.y +' kg';"));
        options.setTooltip(tooltip);

        options.setLegend(new Legend().setEnabled(Boolean.FALSE));

        CustomCoordinatesSeries<String, Float> series1 = new CustomCoordinatesSeries<String, Float>();
        series1.setName(null);
        series1.setData(this.getSeriesData(data));

        options.addSeries(series1);

        return options;
    }

    public int getOptionsType() {
        return optionsType;
    }
}
