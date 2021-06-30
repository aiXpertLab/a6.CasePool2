package com.iwangzhe.mvpchart.model;
import java.util.List;

/**
 * 类：IChartData 图表数据接口
 * 作者： qxc
 * 日期：2018/4/18.
 */
public interface IChartData {
    /**
     * 获得图表数据
     * @param size 数据条数
     * @return 数据集合
     */
    List<ChartDataInfo> getChartData(int size);
}
