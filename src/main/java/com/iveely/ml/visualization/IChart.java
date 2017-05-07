package com.iveely.ml.visualization;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.CategoryDataset;

/**
 * Created by liufanping on 17/5/6.
 */
public interface IChart {

    ChartPanel createChart(final String title,
                           final CategoryDataset dataset,
                           final String xlabel,
                           final String ylable);
}
