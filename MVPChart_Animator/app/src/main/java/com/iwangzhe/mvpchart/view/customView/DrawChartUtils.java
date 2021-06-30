package com.iwangzhe.mvpchart.view.customView;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import com.iwangzhe.mvpchart.model.ChartDataInfo;

import java.util.List;

/**
 * 类：ChartUtils
 * 作者： qxc
 * 日期：2018/4/19.
 */
public class DrawChartUtils {
    private Canvas canvas;//画布
    private Paint paint;//画笔
    private int canvasWidth;//画布宽度
    private int canvasHeight;//画布高度
    private int padding;//View边界间隔

    private final String color_bg = "#343643";//背景色
    private final String color_bg_line = "#999dd2";//背景色
    private final String color_line = "#7176ff";//线颜色
    private final String color_text = "#ffffff";//文本颜色

    List<ChartDataInfo> showData;//图表数据

    private static DrawChartUtils chartUtils;
    public static DrawChartUtils getInstance(){
        if(chartUtils == null){
            synchronized (DrawChartUtils.class){
                if(chartUtils == null){
                    chartUtils = new DrawChartUtils();
                }
            }
        }
        return chartUtils;
    }

    //绘制图表
    public void drawChart(Canvas canvas, Paint paint, int canvasWidth, int canvasHeight, int padding, List<ChartDataInfo> showData) {
        //初始化画布、画笔等数据
        this.canvas = canvas;
        this.paint = paint;
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.padding = padding;
        this.showData = showData;
        if(canvas == null || paint==null || canvasWidth<=0 ||canvasHeight<=0||showData==null || showData.size() ==0){
            return;
        }

        //绘制图表背景
        drawBg();
        //绘制图表线
        drawLine();
    }

    //绘制图表背景
    private void drawBg(){
        //绘制背景色
        canvas.drawColor(Color.parseColor(color_bg));

        //绘制背景坐标轴线
        drawBgAxisLine();
    }

    //绘制图表背景坐标轴线
    private void drawBgAxisLine(){
        //5条线：表示横纵各画5条线
        int lineNum = 5;
        Path path = new Path();

        //x、y轴间隔
        int x_space = canvasWidth / lineNum;
        int y_space = canvasHeight / lineNum;

        //画横线
        for(int i=0; i<=lineNum; i++){
            path.moveTo(0 + padding, i * y_space+ padding);
            path.lineTo(canvasWidth+ padding, i * y_space+ padding);
        }

        //画纵线
        for(int i=0; i<=lineNum; i++){
            path.moveTo(i * x_space+ padding, 0 + padding);
            path.lineTo(i * x_space+ padding, canvasHeight+ padding);
        }

        //设置画笔颜色
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor(color_bg_line));
        //画路径
        canvas.drawPath(path, paint);
    }

    //绘制图表线（数据曲线）
    private void drawLine(){
        if(showData == null){
            return;
        }
        int size = showData.size();

        //画布自适应显示数据（即：画布的宽度应显示全量的图表数据）
        //x轴间隔
        float x_space = canvasWidth / size;
        //y轴最大最小值区间对应画布高度（即画布的高度应显示全量的图表数据）
        float max = getMaxData();
        float min = getMinData();

        float pre_x = 0;
        float pre_y = 0;
        Path path = new Path();

        //从左向右画图
        //将数值转化成对应的坐标值
        for(int i=0; i<size; i++){
            float num = showData.get(i).getNum();
            float x = (i*x_space) + (x_space/2)+ padding;
            float y = (num-min)/(max - min)*canvasHeight+ padding;

            if(i == 0){
                path.moveTo(x,y);
            }else {
                path.quadTo(pre_x, pre_y, x, y);
            }
            pre_x = x;
            pre_y = y;
            drawText(String.valueOf(showData.get(i).getNum()),x,y);
        }

        //设置画笔颜色
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor(color_line));
        //画路径
        canvas.drawPath(path, paint);

        drawAxisXText();
    }

    //画坐标轴文本
    private void drawAxisXText(){
        String start = showData.get(0).getDate();
        String end = showData.get(showData.size()-1).getDate();

        //设置画笔颜色
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setTextSize(40);
        paint.setColor(Color.parseColor(color_text));

        float width_text = paint.measureText(end);

        //开始文本位置
        float x_start = padding;
        float y_start = canvasHeight + padding - paint.descent() - paint.ascent() +10;
        //绘制开始文本
        canvas.drawText(start, x_start, y_start, paint);

        //结束文本位置
        float x_end = canvasWidth + padding - width_text;
        float y_end = canvasHeight + padding-paint.descent()-paint.ascent() +10;
        canvas.drawText(end, x_end, y_end, paint);
    }

    //画线条文本
    private void drawText(String text, float x, float y){
        //设置画笔颜色
        paint.setStrokeWidth(2);
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(color_text));
        canvas.drawText(text, x, y, paint);
    }

    //获得最大值：用于计算、适配Y轴区间
    private int getMaxData(){
        int max = showData.get(0).getNum();
        for(ChartDataInfo info : showData){
            max = info.getNum()>max?info.getNum():max;
        }
        return max;
    }

    //获得最小值：用于计算、适配Y轴区间
    private int getMinData(){
        int min = showData.get(0).getNum();
        for(ChartDataInfo info : showData){
            min = info.getNum()<min?info.getNum():min;
        }
        return min;
    }
}
