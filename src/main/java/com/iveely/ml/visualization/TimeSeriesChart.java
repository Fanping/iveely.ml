package com.iveely.ml.visualization;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.DateTickUnitType;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class TimeSeriesChart {

    public ChartPanel createChart(final String title,
                                  final String xlabel,
                                  final String ylabel,
                                  final TimeSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(title, xlabel, ylabel, dataset);
        ChartUtils.setAntiAlias(chart);
        ChartUtils.setTimeSeriesRender(chart.getPlot(), true, true);
        XYPlot xyplot = (XYPlot) chart.getPlot();
        ChartUtils.setXY_XAixs(xyplot);
        ChartUtils.setXY_YAixs(xyplot);
        DateAxis domainAxis = (DateAxis) xyplot.getDomainAxis();
        domainAxis.setAutoTickUnitSelection(false);
        DateTickUnit dateTickUnit = null;
        if (dataset.getItemCount(0) < 16) {
            dateTickUnit = new DateTickUnit(DateTickUnitType.MONTH, 6, new SimpleDateFormat("yyyy-MM"));
        } else {
            XYLineAndShapeRenderer xyRenderer = (XYLineAndShapeRenderer) xyplot.getRenderer();
            xyRenderer.setBaseItemLabelsVisible(false);
            dateTickUnit = new DateTickUnit(DateTickUnitType.YEAR, 1, new SimpleDateFormat("yyyy"));
        }
        domainAxis.setTickUnit(dateTickUnit);
        ChartUtils.setLegendEmptyBorder(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
