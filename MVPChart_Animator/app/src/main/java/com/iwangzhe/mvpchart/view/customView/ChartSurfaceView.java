package com.iwangzhe.mvpchart.view.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.iwangzhe.mvpchart.model.ChartDataInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类：ChartSurfaceView
 * 作者： qxc
 * 日期：2018/4/19.
 */
public class ChartSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    SurfaceHolder holder;
    Timer timer;
    List<ChartDataInfo> data;//总数据
    List<ChartDataInfo> showData;//当前绘制的数据
    ExecutorService threadPool;//线程池

    Canvas canvas;//画布
    Paint paint;//画笔
    int canvasWidth;//画布宽度
    int canvasHeight;//画布高度
    int padding = 100;//边界间隔

    public ChartSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        initPaint();
    }

    private void initView(){
        holder = getHolder();
        holder.addCallback(this);
        holder.setKeepScreenOn(true);
        threadPool = Executors.newCachedThreadPool();//缓存线程池
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

    //设置图表数据源
    public void setDataSource(List<ChartDataInfo> data){
        this.data = data;
        this.showData = new ArrayList<>();

        if(timer!=null){
            timer.cancel();
        }
        if(canvasWidth > 0){
            startTimer();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        canvasWidth = getWidth() - padding * 2;
        canvasHeight = getHeight() - padding * 2;
        startTimer();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    int index;
    private void startTimer(){
        index = 0;
        timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                index += 1;
                showData.clear();
                showData.addAll(data.subList(0,index));
                //开启子线程 绘制页面，并使用线程池管理
                threadPool.execute(new ChartRunnable());
                if(index>=data.size()){
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 0 , 20);
    }

    //子线程
    class ChartRunnable implements Runnable{
        @Override
        public void run() {
            canvas = holder.lockCanvas();

            DrawChartUtils.getInstance().drawChart(canvas,paint,canvasWidth,canvasHeight,padding,showData);

            holder.unlockCanvasAndPost(canvas);
        }
    }
}
