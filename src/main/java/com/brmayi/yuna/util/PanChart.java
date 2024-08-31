package com.brmayi.yuna.util;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class PanChart {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("各平台用户数量柱状图");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // 创建数据集
            CategoryDataset dataset = createDataset();

            // 创建柱状图
            JFreeChart barChart = ChartFactory.createBarChart(
                    "各平台用户数量分布", // 图表标题
                    "平台", // X轴标签
                    "用户数量", // Y轴标签
                    dataset, // 数据集
                    PlotOrientation.VERTICAL, // 图表方向
                    true, // 显示图例
                    true, // 显示工具提示
                    false // 显示URL链接
            );

            // 设置字体
            Font font = new Font("Microsoft YaHei", Font.PLAIN, 12);
            barChart.getTitle().setFont(font);
            barChart.getLegend().setItemFont(font);
            barChart.getCategoryPlot().getDomainAxis().setLabelFont(font);
            barChart.getCategoryPlot().getRangeAxis().setLabelFont(font);
            barChart.getCategoryPlot().getDomainAxis().setTickLabelFont(font);
            barChart.getCategoryPlot().getRangeAxis().setTickLabelFont(font);

            // 创建图表面板并添加到框架
            ChartPanel chartPanel = new ChartPanel(barChart);
            frame.add(chartPanel);
            frame.setVisible(true);
        });
    }

    private static CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // 添加数据
        dataset.addValue(354971, "用户数量", "抖音");
        dataset.addValue(114617, "用户数量", "视频号");
        dataset.addValue(44274, "用户数量", "百度");
        dataset.addValue(12448, "用户数量", "快手");
        dataset.addValue(10673, "用户数量", "小鹅通");
        dataset.addValue(2499, "用户数量", "小红书");
        dataset.addValue(610, "用户数量", "网校");
        dataset.addValue(742, "用户数量", "其他");
        dataset.addValue(296, "用户数量", "微信公众号");

        return dataset;
    }
}
