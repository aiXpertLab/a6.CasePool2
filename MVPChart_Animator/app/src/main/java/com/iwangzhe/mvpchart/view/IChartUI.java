package com.iwangzhe.mvpchart.view;

import com.iwangzhe.mvpchart.model.ChartDataInfo;

import java.util.List;

/**
 * 类：IChartView
 * 作者： qxc
 * 日期：2018/4/18.
 */
public interface IChartUI {
    /**
     * 显示图表
     * @param data 数据
     */
    void showChartData(List<ChartDataInfo> data);
}
