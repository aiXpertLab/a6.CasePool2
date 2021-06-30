package com.hypech.leographics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        GraphicsView gv = new GraphicsView(this);
//        setContentView(gv);

        PathEffectView pev = new PathEffectView(this);
        setContentView(pev);
    }
}