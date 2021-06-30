package com.hypech.asrprototype;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hms.mlsdk.asr.MLAsrConstants;

import java.util.ArrayList;

public class OnResultListener extends AppCompatActivity {

    public TextView mShowWords;
    private boolean mIsRecording = false;

    @Override
    public void onResults(ArrayList<String> results) {
        setContentView(R.layout.activity_main);
        mShowWords = findViewById(R.id.textView_showing_words);

        if (results != null && results.size() > 0) {
            if (results.size() == 1) {
                mShowWords.setText(results.get(0) + "\n the end.");
            } else {
                StringBuilder sb = new StringBuilder();
                if (results.size() > 5) {
                    results = (ArrayList<String>) results.subList(0, 5);
                }
                for (String result : results) {
                    sb.append(result).append("\n");
                }
                mShowWords.setText(sb.toString()+"L1_455");
            }
        }
    }

    @Override
    public void onError(int error) {
        showFailedDialog(getPrompt(error));
    }

    @Override
    public void onFinsh() {

    }

    private void showFailedDialog(int res) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(res)
                .setPositiveButton(getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.design_default_color_background));
    }

    private int getPrompt(int errorCode) {
        switch (errorCode) {
            case MLAsrConstants.ERR_NO_NETWORK:
                return R.string.error_no_network;
            case MLAsrConstants.ERR_NO_UNDERSTAND:
                return R.string.error_no_understand;
            case MLAsrConstants.ERR_SERVICE_UNAVAILABLE:
                return R.string.error_service_unavailable;
            default:
                return errorCode;
        }
    }
}
