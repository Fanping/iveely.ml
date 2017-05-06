package com.iveely.ml.visualization;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.CategoryDataset;

import java.awt.*;

public class StackedBarChart implements IChart {


    public ChartPanel createChart(final String title,
                                  final CategoryDataset dataset,
                                  final String xlabel,
                                  final String ylable) {
        JFreeChart chart = ChartFactory.createStackedBarChart(title, xlabel, ylable, dataset);
        ChartUtils.setAntiAlias(chart);
        ChartUtils.setStackBarRender(chart.getCategoryPlot());
        ChartUtils.setXAixs(chart.getCategoryPlot());
        ChartUtils.setYAixs(chart.getCategoryPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

}
