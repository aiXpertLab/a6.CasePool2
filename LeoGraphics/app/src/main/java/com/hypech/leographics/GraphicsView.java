package com.hypech.leographics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class GraphicsView extends View {
    //声明画笔
    Paint paint = null;

    public GraphicsView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置画布背景为白色
        canvas.drawColor(Color.WHITE);
        //设置画笔为红色
        paint.setColor(Color.RED);
        //使用当前画笔绘制一个左上角坐标为80,20，右下角坐标为360,180的矩形
        canvas.drawRect(80, 20, 360, 180, paint);
        //设置画笔为绿色
        paint.setColor(Color.GREEN);
        //使用当前画笔绘制一个圆心坐标为220,100，半径为60的圆
        canvas.drawCircle(220, 100, 60, paint);
    }
}