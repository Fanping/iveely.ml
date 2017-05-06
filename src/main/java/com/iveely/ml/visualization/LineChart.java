package com.iveely.ml.visualization;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart implements IChart{

	public ChartPanel createChart(final String title,
								  final CategoryDataset dataset,
								  final String xlabel,
								  final String ylable) {
		JFreeChart chart = ChartFactory.createLineChart(title, xlabel, ylable, dataset);
		ChartUtils.setAntiAlias(chart);
		ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
		ChartUtils.setXAixs(chart.getCategoryPlot());
		ChartUtils.setYAixs(chart.getCategoryPlot());
		chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
		ChartPanel chartPanel = new ChartPanel(chart);
		return chartPanel;
	}
}
