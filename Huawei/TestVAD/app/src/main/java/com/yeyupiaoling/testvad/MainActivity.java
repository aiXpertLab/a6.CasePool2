package com.yeyupiaoling.testvad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private boolean mSpeaking;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!hasPermission()){
            requestPermission();
        }

        Button start_btn = findViewById(R.id.start_btn);
        Button stop_btn = findViewById(R.id.stop_btn);
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRecord();
                startRecord();
            }
        });
        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecord();
            }
        });

    }

    private int mMinBufferSize;
    private AudioRecord mRecorder;

    private void initRecord() {
        mMinBufferSize = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, mMinBufferSize * 2);
    }

    private boolean mIsRecording = false;

    // 开始录音
    private void startRecord() {
        mIsRecording = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO);
                    int readSize;
                    mMinBufferSize = 320;
                    short[] audioData = new short[mMinBufferSize];
                    if (mRecorder.getState() != AudioRecord.STATE_INITIALIZED) {
                        stopRecord();
                        return;
                    }
                    mRecorder.startRecording();

                    while (mIsRecording) {
                        if (null != mRecorder) {
                            readSize = mRecorder.read(audioData, 0, mMinBufferSize);

                            if (readSize == AudioRecord.ERROR_INVALID_OPERATION || readSize == AudioRecord.ERROR_BAD_VALUE) {
                                continue;
                            }
                            if (readSize != 0 && readSize != -1) {
                                // 语音活动检测
                                mSpeaking = webRtcVad_Process(audioData, 0, readSize);
                                if (mSpeaking) {
                                    Log.d(TAG, ">>>>>正在讲话");
                                } else {
                                    Log.d(TAG, "=====当前无声音");
                                }
                            } else {
                                break;
                            }
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void stopRecord() {
        mIsRecording = false;
        if (mRecorder != null) {
            mRecorder.stop();
        }
    }


    public native boolean webRtcVad_Process(short[] audioData, int offsetInshort, int readSize);

    // check had permission
    private boolean hasPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            return true;
        }
    }

    // request permission
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
}
