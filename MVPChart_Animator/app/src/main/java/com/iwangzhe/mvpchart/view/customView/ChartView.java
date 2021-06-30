package com.iwangzhe.mvpchart.view.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.iwangzhe.mvpchart.model.ChartDataInfo;

import java.util.List;

/**
 * 类：ChartView
 * 作者： qxc
 * 日期：2018/4/18.
 */
public class ChartView extends View{
    int canvasWidth;//画布宽度
    int canvasHeight;//画布高度
    int padding = 100;//边界间隔
    Paint paint;//画笔

    List<ChartDataInfo> data;//数据

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔属性
        initPaint();
    }

    //设置图表数据
    public void setDataSet(List<ChartDataInfo> data){
        this.data = data;

        //强制重绘
        invalidate();
    }

    //初始化画笔属性
    private void initPaint(){
        //设置防锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //绘制图形样式
        //Paint.Style.STROKE描边
        //Paint.Style.FILL内容
        //Paint.Style.FILL_AND_STROKE内容+描边
        paint.setStyle(Paint.Style.STROKE);
        //设置画笔宽度
        paint.setStrokeWidth(1);
    }

    //每一次外观变化，都会调用该方法
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获得画布宽度
        this.canvasWidth = getWidth() - padding * 2;
        //获得画布高度
        this.canvasHeight = getHeight() - padding * 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //每次重绘，绘制图表信息
        DrawChartUtils.getInstance().drawChart(canvas, paint, canvasWidth,canvasHeight,padding,data);
    }
}
