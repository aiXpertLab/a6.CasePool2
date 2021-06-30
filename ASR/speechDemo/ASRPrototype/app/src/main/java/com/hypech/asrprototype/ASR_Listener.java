package com.hypech.asrprototype;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.MLAsrListener;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;

import java.util.ArrayList;

public class ASR_Listener implements MLAsrListener {
    private final static String TAG = "HWRecognition Listner";

    public OnResultsReady mListener;
    ArrayList<String> mResultsList = new ArrayList<>();

    public ASR_Listener(OnResultsReady listener) {
        try {
            mListener = listener;
        } catch (ClassCastException e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onStartListening() {
        Log.d(TAG, "onStartListening--");           // 录音器开始接收声音。
    }

    @Override
    public void onStartingOfSpeech() {
        Log.d(TAG, "onStartingOfSpeech--");     // 用户开始讲话，即语音识别器检测到用户开始讲话。
    }

    // 返回给用户原始的PCM音频流和音频能量，该接口并非运行在主线程中，返回结果需要在子线程中处理。
    @Override
    public void onVoiceDataReceived(byte[] data, float energy, Bundle bundle) {
        int length = data == null ? 0 : data.length;
        Log.d(TAG, "onVoiceDataReceived-- data.length=" + length);
        Log.d(TAG, "onVoiceDataReceived-- energy =" + energy);
    }

    // 从MLAsrRecognizer接收到持续语音识别的文本，该接口并非运行在主线程中，返回结果需要在子线程中处理。
    @Override
    public void onRecognizingResults(Bundle partialResults) {
        if (partialResults != null && mListener != null) {
            mResultsList.clear();
            mResultsList.add(partialResults.getString(MLAsrRecognizer.RESULTS_RECOGNIZING));
            mListener.onResults(mResultsList);
            Log.d(TAG, "onRecognizingResults is_sv " + partialResults);
        }
    }

    @Override
    public void onResults(Bundle results) {
        Log.e(TAG, "onResults");
        if (results != null && mListener != null) {
            int i = 1;
            mResultsList.clear();
            mResultsList.add(results.getString(MLAsrRecognizer.RESULTS_RECOGNIZED));
            mListener.onFinsh();
            Log.d(TAG, "onResults is " + results);
        }
    }

    @Override
    public void onError(int error, String errorMessage) {
        Log.e(TAG, "onError: " + errorMessage);
        // If you don't add this, there will be no response after you cut the network
        if (mListener != null) {
            mListener.onError(error);
        }
    }

    @Override
    // 通知应用状态发生改变，该接口并非运行在主线程中，返回结果需要在子线程中处理。
    //sv listener 不关闭，但语音识别还是关闭了
    public void onState(int state, Bundle params) {
        Log.e(TAG, "onState: L_142 " + state);
        if (state == MLAsrConstants.STATE_NO_SOUND_TIMES_EXCEED) {
            // onState回调中的状态码，表示6s内没有检测到结果。Constant Value：3
            if (mListener != null) {
                mListener.onFinsh();
            }
            Log.e(TAG, "onState: L_142 no sound " + state);
        }
    }
}

