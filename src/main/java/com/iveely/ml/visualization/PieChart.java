package com.iveely.ml.visualization;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;

public class PieChart {

    public ChartPanel createChart(String title, DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(title, dataset);
        ChartUtils.setAntiAlias(chart);
        ChartUtils.setPieRender(chart.getPlot());
        chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }
}
