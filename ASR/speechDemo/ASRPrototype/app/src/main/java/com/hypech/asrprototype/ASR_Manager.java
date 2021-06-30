package com.hypech.asrprototype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;

import java.util.ArrayList;

public class ASR_Manager {
    private final static String TAG = "HW_Manager";
    private OnResultListener listener = new OnResultListener();

    protected MLAsrRecognizer mRecognizer;
    protected Intent mIntent;

    public OnResultListener mListener;
    ArrayList<String> mResultsList = new ArrayList<>();

    public ASR_Manager(Context context, OnResultListener listener) {
        try {
            mListener = listener;
        } catch (ClassCastException e) {
            Log.e(TAG, e.toString());
        }

        mRecognizer = MLAsrRecognizer.createAsrRecognizer(context);   //用户调用接口创建一个语音识别器。
        mRecognizer.setAsrListener(new ASR_Listener(mListener));
    }

    public void startListening() {
        Log.d(TAG, "startListening()");
        mSpeechRecognizer.startRecognizing(mIntent);
        int i = 10000;
        Log.d(TAG, "startListening()73 " + i);
    }

    public void destroy() {
        Log.d(TAG, "onDestroy");
        if (mSpeechRecognizer != null) {
//sv            mSpeechRecognizer.destroy();
            //sv          mSpeechRecognizer = null;
        }

    }
}
