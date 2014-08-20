package com.tieto.it2014.ui.weight.detail;

import com.googlecode.wickedcharts.highcharts.options.Axis;
import com.googlecode.wickedcharts.highcharts.options.AxisType;
import com.googlecode.wickedcharts.highcharts.options.ChartOptions;
import com.googlecode.wickedcharts.highcharts.options.DateTimeLabelFormat;
import com.googlecode.wickedcharts.highcharts.options.DateTimeLabelFormat.DateTimeProperties;
import com.googlecode.wickedcharts.highcharts.options.Function;
import com.googlecode.wickedcharts.highcharts.options.Options;
import com.googlecode.wickedcharts.highcharts.options.SeriesType;
import com.googlecode.wickedcharts.highcharts.options.Title;
import com.googlecode.wickedcharts.highcharts.options.Tooltip;
import com.googlecode.wickedcharts.highcharts.options.series.Coordinate;
import com.googlecode.wickedcharts.highcharts.options.series.CustomCoordinatesSeries;
import com.googlecode.wickedcharts.wicket6.highcharts.Chart;
import com.tieto.it2014.domain.weight.entity.Weight;
import com.tieto.it2014.domain.weight.query.WeightQuery;
import com.tieto.it2014.ui.session.UserSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class ChartPanel extends Panel {

    private static final long serialVersionUID = 1L;

    @SpringBean
    private WeightQuery weightQuery;

    private Options options = new Options();
    private Chart chart;

    public ChartPanel(String id) {
        this(id, null);
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

    public Options getDefaultOptions(List<Weight> data) {
        Options options = new Options();

        options.setChartOptions(new ChartOptions().setType(SeriesType.SPLINE));

        options.setTitle(new Title("Weight statistics"));
        options.setSubtitle(new Title("Nothing tastes as good as being thin feels"));

        Axis xAxis = new Axis();
        xAxis.setType(AxisType.DATETIME);

        DateTimeLabelFormat dateTimeLabelFormat = new DateTimeLabelFormat()
                .setProperty(DateTimeProperties.MONTH, "%e. %b")
                .setProperty(DateTimeProperties.YEAR, "%b");

        xAxis.setDateTimeLabelFormats(dateTimeLabelFormat);
        xAxis.setTitle(new Title("Time"));
        options.setxAxis(xAxis);

        Axis yAxis = new Axis();
        yAxis.setTitle(new Title("Weight"));
        yAxis.setMin(this.getMinWeightValue(data));
        options.setyAxis(yAxis);

        Tooltip tooltip = new Tooltip();
        tooltip.setFormatter(new Function(
                "return '<b>'+ this.series.name +'</b><br/>'+Highcharts.dateFormat('%H:%M', this.x) +': '+ this.y +' kg';"));
        options.setTooltip(tooltip);

        CustomCoordinatesSeries<String, Float> series1 = new CustomCoordinatesSeries<String, Float>();
        series1.setName("Weight");
        series1.setData(this.getSeriesData(data));

        options.addSeries(series1);

        return options;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        List<Weight> chartData = weightQuery.result(UserSession.get().getUser().imei);

        options = getDefaultOptions(chartData);
        chart = new Chart("chart", options);
        add(chart);
    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        List<Weight> chartData = weightQuery.result(UserSession.get().getUser().imei);
        options = getDefaultOptions(chartData);
        chart.setOptions(options);
    }

    private Float getMinWeightValue(List<Weight> data) {
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

}
