package appswing.models;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

public class Graph extends JPanel {

    XYSeries velocityX;
    XYSeriesCollection dataset;

    public Graph(String title, String xLabel, String yLabel, String seriesLabel)
    {
        //dodanie danych
        velocityX = new XYSeries(seriesLabel);

        dataset = new XYSeriesCollection();
        dataset.addSeries(velocityX);
        JFreeChart chart = ChartFactory.createXYLineChart(title, xLabel,yLabel,dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300,250));

        this.add(chartPanel);
    }

    public void add(double x, double y)
    {
        velocityX.addOrUpdate(x,y);
    }
    public void clear()
    {
        velocityX.clear();
    }

}
