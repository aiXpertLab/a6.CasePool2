package com.iwangzhe.mvpchart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 类：ChartDataImpl 图表数据实现类
 * 作者： qxc
 * 日期：2018/4/18.
 */
public class ChartDataImpl implements IChartData{
    private int maxNum = 100;

    /**
     * 返回随机的图表数据
     * @param size 数据条数
     * @return 图表数据集合
     */
    @Override
    public List<ChartDataInfo> getChartData(int size) {
        List<ChartDataInfo> data = new ArrayList<>();
        Random random = new Random();
        random.setSeed(ChartDateUtils.getDateNow());
        for(int i = size-1; i>=0 ; i--){
            ChartDataInfo dataInfo = new ChartDataInfo(ChartDateUtils.getDate(i), random.nextInt(maxNum));
            data.add(dataInfo);
        }
        return data;
    }
}
