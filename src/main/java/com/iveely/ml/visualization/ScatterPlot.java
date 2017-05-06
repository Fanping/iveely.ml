package com.iveely.ml.visualization;


import java.awt.Color;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;

/**
 * Created by liufanping on 17/4/14.
 */
public class ScatterPlot {


    private String title;

    private String xAxisLabel;

    private String yAxisLable;

    /**
     * A demonstration application showing a scatter plot.
     **/
    public ScatterPlot(String title, String xAxisLabel, String yAxisLable) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLable = yAxisLable;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createScatterPlot(this.title,
                this.xAxisLabel, this.yAxisLable, dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setNoDataMessage("NO DATA");
        XYLineAndShapeRenderer renderer
                = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setSeriesOutlinePaint(0, Color.black);
        renderer.setUseOutlinePaint(true);

        NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
        domainAxis.setAutoRangeIncludesZero(false);
        return chart;

    }

    public JPanel createPanel(XYDataset dataset) {
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setHorizontalAxisTrace(true);
        chartPanel.setPopupMenu(null);
        chartPanel.setDomainZoomable(true);
        chartPanel.setRangeZoomable(true);
        return chartPanel;
    }
}
