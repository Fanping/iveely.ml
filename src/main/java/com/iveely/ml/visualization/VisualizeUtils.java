/* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
* http:* www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.iveely.ml.visualization;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;

/**
 * Author:liufanping@iveely.com
 **/
public class VisualizeUtils {

    private enum Type {
        BAR_CHART,
        LINE_CHART,
        STACKEDBAR_CHART
    }

    public static void drawBarChart(final String title,
                                    final String xlabel,
                                    final String ylabel,
                                    final DefaultCategoryDataset dataset) {
        draw(Type.BAR_CHART, title, xlabel, ylabel, dataset);
    }

    public static void drawLineChart(final String title,
                                     final String xlabel,
                                     final String ylabel,
                                     final DefaultCategoryDataset dataset) {
        draw(Type.LINE_CHART, title, xlabel, ylabel, dataset);
    }

    public static void drawStackedBarChart(final String title,
                                           final String xlabel,
                                           final String ylabel,
                                           final DefaultCategoryDataset dataset) {
        draw(Type.STACKEDBAR_CHART, title, xlabel, ylabel, dataset);
    }

    public static void drawScatterPlot(final String title,
                                       final String xlabel,
                                       final String ylabel,
                                       final XYDataset dataset) {
        final JFrame frame = getFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JPanel jpanel = new ScatterPlot(title, xlabel, ylabel).createPanel(dataset);
                frame.getContentPane().add(jpanel);
                frame.setVisible(true);
            }
        });
    }

    public static void drawTimeSeriesChart(final String title,
                                           final String xlabel,
                                           final String ylabel,
                                           final TimeSeriesCollection dataset) {
        final JFrame frame = getFrame();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChartPanel chartPanel = new TimeSeriesChart().createChart(title,
                        xlabel, ylabel, dataset);
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });
    }


    public static void drawPie(final String title,
                               final DefaultPieDataset dataset) {
        final JFrame frame = getFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChartPanel chartPanel = new PieChart().createChart(title, dataset);
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });
    }

    private static IChart getChart(final Type type) {
        switch (type) {
            case BAR_CHART:
                return new BarChart();
            case LINE_CHART:
                return new LineChart();
            case STACKEDBAR_CHART:
                return new StackedBarChart();
            default:
                return null;
        }
    }

    private static void draw(final Type type,
                             final String title,
                             final String xlabel,
                             final String ylabel,
                             final DefaultCategoryDataset dataset) {
        final JFrame frame = getFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ChartPanel chartPanel = getChart(type).createChart(title, dataset, xlabel, ylabel);
                frame.getContentPane().add(chartPanel);
                frame.setVisible(true);
            }
        });
    }

    private static JFrame getFrame() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 420);
        frame.setLocationRelativeTo(null);
        return frame;
    }
}
