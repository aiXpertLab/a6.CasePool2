package com.hypech.leographics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PathEffectView extends View {
    private Paint paint, p2;
    private Path mainPath;
    private PathEffect[] effects;
    public PathEffectView(Context context) {
        super(context);
        inView();
    }

    private void inView(){
        paint=new Paint();
        p2 = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.DKGRAY);
        mainPath=new Path();
        mainPath.moveTo(0,0);
        for (int i = 0; i <= 30; i++) {
            mainPath.lineTo(i*35, (float) (Math.random()*100));
        }
        effects=new PathEffect[6];
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        effects[0]=null;
        effects[1]=new CornerPathEffect(200);
        effects[2]=new DiscretePathEffect(3,5);
        effects[3]=new DashPathEffect(new float[]{20,10,5,10},0);
        Path path1 =new Path();
        path1.addRect(0,0,8,8,Path.Direction.CCW);
        effects[4]=new PathDashPathEffect(path1,12,0,PathDashPathEffect.Style.ROTATE);
        effects[5]=new ComposePathEffect(effects[3],effects[1]);
        for (PathEffect effect : effects) {
            paint.setPathEffect(effect);
            canvas.drawPath(mainPath, paint);
            canvas.translate(0, 200);
        }


        Path path2 = new Path();
        path2.moveTo(200,500);
        path2.lineTo(400,100);
        path2.lineTo(600,500);
        path2.close();

        paint.setPathEffect(new CornerPathEffect(50));
        paint.setColor(Color.parseColor("#4def6f"));
        canvas.drawPath(path2, paint);
        paint.setPathEffect(new CornerPathEffect(100));
        paint.setColor(Color.parseColor("#451395"));
        canvas.drawPath(path2, paint);

            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(40);
            Path path = new Path();
            path.moveTo(550, 550);
            path.lineTo(900, 300);
            path.lineTo(1000, 550);
            canvas.drawPath(path, paint);
            //蓝色辅助线
            Paint tempPaint = new Paint();
            tempPaint.setStyle(Paint.Style.STROKE);
            tempPaint.setColor(Color.BLUE);
            tempPaint.setStrokeWidth(2);
            tempPaint.setPathEffect(new DashPathEffect(new float[]{20, 20}, 0));
            Path helpPath = new Path();
            helpPath.moveTo(550, 550);
            helpPath.lineTo(900, 300);
            helpPath.lineTo(1000, 550);
            canvas.drawPath(helpPath, tempPaint);

        canvas.drawPath(path2, p2);
        p2.setColor(Color.GREEN);
        p2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(400,100,120,p2);
        p2.setColor(Color.RED);
        p2.setStyle(Paint.Style.STROKE);
        p2.setStrokeWidth(13);
        canvas.drawCircle(400, 100, 160, p2);

    }
}