package com.iwangzhe.mvpchart.presenter;

import com.iwangzhe.mvpchart.model.ChartDataImpl;
import com.iwangzhe.mvpchart.model.ChartDataInfo;
import com.iwangzhe.mvpchart.model.IChartData;
import com.iwangzhe.mvpchart.view.IChartUI;

import java.util.List;

/**
 * 类：ChartPresenter
 * 作者： qxc
 * 日期：2018/4/18.
 */
public class ChartPresenter {
    private IChartUI iChartView;
    private IChartData iChartData;

    public ChartPresenter(IChartUI iChartView) {
        this.iChartView = iChartView;
        this.iChartData = new ChartDataImpl();
    }

    //获取图表数据的业务逻辑
    public void getChartData(){
        //请求的数据数量
        int size = 50;
        //获得图表数据
        List<ChartDataInfo> data = iChartData.getChartData(size);
        //把数据设置给UI
        iChartView.showChartData(data);
    }
}
