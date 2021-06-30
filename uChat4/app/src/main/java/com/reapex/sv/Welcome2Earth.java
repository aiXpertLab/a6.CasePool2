package com.reapex.sv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome2Earth extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        final View view = View.inflate(this, R.layout.a_splash_earth, null);
        // MyUtil.hideSystemUI(this);
        View decorView = this.getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(view);
        super.onCreate(savedInstanceState);
        Log.e("Welcome2Earth", "启动.");

        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        view.setAnimation(animation);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * Dispatch onStop() to all fragments.
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (MySP.getInstance().getString("LOGIN").equals("yes")) {
            // 已登录，跳至主页面
            startActivity(new Intent(Welcome2Earth.this, Welcome3Main.class));
            finish();
        }
    }

    public void myClick(View view) {
        if (view.getId() == R.id.btn_login) {
            startActivity(new Intent(Welcome2Earth.this, Login.class));
        }else if (view.getId() == R.id.btn_register) {
            startActivity(new Intent(Welcome2Earth.this, Register.class));
        }
    }
}