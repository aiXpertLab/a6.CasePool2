package com.reapex.sv.frag3me;

import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.reapex.sv.R;
import com.reapex.sv.db.AChatDB;
import com.reapex.sv.MySP;

import androidx.annotation.Nullable;

public class MeMoreRegion extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    TextView mTitleTv;
    EditText mRegion;
    View mNickView;
    TextView mSaveTv;

    MySP myUser = MySP.getInstance();
    AChatDB db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_frag3_me_more_region);

        mTitleTv= findViewById(R.id.text_view_title);
        mRegion = findViewById(R.id.edit_text_region);
        mNickView= findViewById(R.id.v_nick);

        mSaveTv= findViewById(R.id.tv_save);
        mSaveTv.setTextColor(0xFFFFFFFF);
        mSaveTv.setEnabled(true);

        MySP.getInstance().init(this);

        initView();

        mSaveTv.setOnClickListener(view -> {
            Log.e(TAG, "savidd ng ... ");
            String userRegion = mRegion.getText().toString();
            updateUserRegion(myUser.getString("PHONE"), userRegion);
        });
    }

    private void initView() {
        TextPaint paint = mTitleTv.getPaint();
        paint.setFakeBoldText(true);

        if (null !=MySP.getInstance().getString("REGION")) {
            mRegion.setText(MySP.getInstance().getString("REGION"));
        }else {
            mRegion.setText(getString(R.string.country_china));
        }

        mRegion.setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                mNickView.setBackgroundColor(getColor(R.color.divider_green));
            } else {
                mNickView.setBackgroundColor(getColor(R.color.divider_grey));
            }
        });
    }

    public void back(View view) {
        finish();
    }

    private void updateUserRegion(String userId, final String userRegion) {
        MySP.getInstance().saveString("REGION", userRegion);
        db = AChatDB.getDatabase(this);
        db.getUserDao().updateRegion(MySP.getInstance().getString("PHONE"), userRegion);
        finish();
    }
}