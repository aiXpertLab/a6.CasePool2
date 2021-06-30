package com.iwangzhe.mvpchart.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.iwangzhe.mvpchart.R;
import com.iwangzhe.mvpchart.model.ChartDataInfo;
import com.iwangzhe.mvpchart.presenter.ChartPresenter;
import com.iwangzhe.mvpchart.view.customView.ChartView;

import java.util.List;

public class MainActivity extends Activity implements IChartUI {
    ChartPresenter chartPresenter;
    ChartView cv;
    Button btn;
    Button btnSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化presenter
        chartPresenter = new ChartPresenter(this);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //初始化事件
        initEvent();
    }

    //初始化控件
    private void initView() {
        //初始化控件
        cv = (ChartView) findViewById(R.id.cv);
        btn = (Button) findViewById(R.id.btn);
        btnSurface = (Button) findViewById(R.id.btnSurface);
    }

    //初始化数据
    private void initData() {
        chartPresenter.getChartData();
    }

    //初始化事件
    private void initEvent() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartPresenter.getChartData();
            }
        });
        btnSurface.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SurfaceChartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showChartData(List<ChartDataInfo> data) {
        for(int i=0;i<data.size();i++){
            Log.i("ChartData", data.get(i).getDate() +"------"+data.get(i).getNum());
        }
        //图表控件设置数据源
        cv.setDataSet(data);
    }
}
