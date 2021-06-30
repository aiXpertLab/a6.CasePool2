package com.reapex.sv.frag3me;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.reapex.sv.Constant;
import com.reapex.sv.R;
import com.reapex.sv.db.AChatDB;
import com.reapex.sv.MySP;


import androidx.annotation.Nullable;

public class MeMoreGender extends AppCompatActivity implements View.OnClickListener {
    TextView mTitleTv,     mSaveTv;

    RelativeLayout mMaleRl,  mFemaleRl,  mNoSayRl;
    ImageView mMaleIv,      mFemaleIv,  mNoSayIv;

    String mSex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_frag3_set_gender);

        mTitleTv= findViewById(R.id.text_view_title);
        mSaveTv= findViewById(R.id.tv_save);

        mMaleRl   = findViewById(R.id.rl_male);
        mFemaleRl = findViewById(R.id.rl_female);
        mNoSayRl = findViewById(R.id.rl_nosay);

        mMaleIv   = findViewById(R.id.iv_male);
        mFemaleIv = findViewById(R.id.iv_female);
        mNoSayIv  = findViewById(R.id.iv_nosay);

        mMaleRl.setOnClickListener(this);
        mFemaleRl.setOnClickListener(this);
        mNoSayRl.setOnClickListener(this);
        mSaveTv.setOnClickListener(this);

        initView();
    }

    private void initView() {
        TextPaint paint = mTitleTv.getPaint();
        paint.setFakeBoldText(true);
        renderSex();
    }

    public void back(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()== R.id.rl_male){
            mMaleIv.setVisibility(View.VISIBLE);
            mFemaleIv.setVisibility(View.GONE);
            mNoSayIv.setVisibility(View.GONE);

            mSex = Constant.USER_SEX_MALE;

        }else if (view.getId()== R.id.rl_female){
            mMaleIv.setVisibility(View.GONE);
            mFemaleIv.setVisibility(View.VISIBLE);
            mNoSayIv.setVisibility(View.GONE);

            mSex = Constant.USER_SEX_FEMALE;
        }else if (view.getId()== R.id.rl_nosay){
            mMaleIv.setVisibility(View.GONE);
            mFemaleIv.setVisibility(View.GONE);
            mNoSayIv.setVisibility(View.VISIBLE);

            mSex = Constant.USER_SEX_NOSAY;
        }else if (view.getId()==R.id.tv_save){
            int p =1;
            MySP.getInstance().saveString("GENDER",mSex);
            AChatDB db = AChatDB.getDatabase(this);
                    db.getUserDao().updateGender(MySP.getInstance().getString("PHONE"), mSex);
            finish();
        }
    }

    private void renderSex() {
        if (Constant.USER_SEX_MALE.equals(MySP.getInstance().getString("GENDER"))) {
            // 男
            mMaleIv.setVisibility(View.VISIBLE);
            mFemaleIv.setVisibility(View.GONE);
            mNoSayIv.setVisibility(View.GONE);
        } else if (Constant.USER_SEX_FEMALE.equals(MySP.getInstance().getString("GENDER"))) {
            // 女
            mMaleIv.setVisibility(View.GONE);
            mFemaleIv.setVisibility(View.VISIBLE);
            mNoSayIv.setVisibility(View.GONE);
        } else {
            // 鲲
            mMaleIv.setVisibility(View.GONE);
            mFemaleIv.setVisibility(View.GONE);
            mNoSayIv.setVisibility(View.VISIBLE);
        }
    }
}
