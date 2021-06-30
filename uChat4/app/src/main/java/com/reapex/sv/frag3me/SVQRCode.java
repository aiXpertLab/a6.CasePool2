package com.reapex.sv.frag3me;

import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.reapex.sv.R;
import com.reapex.sv.Constant;
import com.reapex.sv.MySP;

/**
 * @author  LeoReny@hypech.com
 * @version 1.0
 * @since   2021-04-07
 */
public class SVQRCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_qr_code);

        ImageView mAvatar = findViewById(R.id.iv_avatar);
        ImageView mSexIv  = findViewById(R.id.iv_sex);
            mSexIv.setImageResource(R.mipmap.icon_sex_female);
            mAvatar.setImageResource(R.mipmap.customerservicefemale);

        TextView mTitleTv= findViewById(R.id.text_view_title);
        TextPaint paint = mTitleTv.getPaint();
        paint.setFakeBoldText(true);

        TextView mBelowName= findViewById(R.id.text_view_below_company);
        TextView mBelowQR  = findViewById(R.id.text_view_below_qr_code);

        String src = getIntent().getStringExtra("from");

        if (src.equals("AccountSecurity")){
            mBelowName.setText(getString(R.string.security_center));
            mBelowQR.setText(getString(R.string.security_center_msg));
        }else if (src.equals("web")){
            mTitleTv.setText(getString(R.string.company_web));
            mBelowName.setText(getString(R.string.url));
            mBelowQR.setText("");
        }else if (src.equals("help")){
            mBelowName.setText(getString(R.string.uchat));
            mBelowQR.setText(getString(R.string.scan_qr_code_and_add_friends));
        }else if (src.equals("Frag3MeProfile")){
            mBelowName.setText(getString(R.string.uchat));
            mBelowQR.setText(getString(R.string.scan_qr_code_and_add_friends));
        }else if (src.equals("complain")){
            mBelowName.setText(getString(R.string.complain));
            mBelowQR.setText(getString(R.string.scan_qr_code_and_add_friends));
        }else if (src.equals("upgrade")){
            mBelowName.setText(getString(R.string.uchat));
            mBelowQR.setText(getString(R.string.upgrade));
        }
    }

    public void back(View view) {        finish();    }
}