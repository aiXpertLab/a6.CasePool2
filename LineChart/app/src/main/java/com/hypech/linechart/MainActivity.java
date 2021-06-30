package com.hypech.linechart;

import android.os.Bundle;
import android.app.Activity;
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化GraphicsView
        GraphicsView gv = new GraphicsView(this);
        /* 在以前我们通过setContentView(R.layout.activity_main)显示布局文件
         * 本例中使用GraphicsView对象gv代替以前布局文件
         */
        setContentView(gv);
    }
}

