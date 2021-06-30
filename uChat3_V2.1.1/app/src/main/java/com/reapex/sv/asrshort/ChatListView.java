package com.reapex.sv.asrshort;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hms.mlsdk.asr.MLAsrListener;
import com.huawei.hms.mlsdk.asr.MLAsrRecognizer;
import com.reapex.sv.BaseActivity;
import com.reapex.sv.R;
import com.reapex.sv.db.AMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;

// the pull-down notification bar does not go through any life cycle。It's onWindowFocusChanged. We don't monitor it here.
// back, hide will invoke onPause. Ignore onResume.
// asrRecognizer will kill itself after 6s silence, or 60s in talk.

public class ChatListView extends BaseActivity  implements View.OnClickListener {
    static ASRListener     asrListener;        // = new ASRListener();
    static MLAsrRecognizer asrRecognizer;      // = MLAsrRecognizer.createAsrRecognizer();    //a 用户调用接口创建一个语音识别器。
    static AudioRecord     mRecorder;

    static {System.loadLibrary("native-lib");}
    boolean mStartVAD, mSpeaking, firstWord = true, clickStop;

    MaterialButton mBtnStart, mBtnStop;

    static  String   pUserId, pUserName;
    static  int      pUserAvaR, mMinBufferSize, mAudioSize;

    List<AMessage>    aList; // = new ArrayList<AMessage>();
    ListView          aListView;
    MyListViewAdapter aAdapter;
    Intent            mIntent;

    ImageView         imageViewRecording;
    AnimationDrawable animationRecording;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_chat_list_view);               //a_activity_chat is listview

        pUserId   = getIntent().getStringExtra("from1");
        pUserName = getIntent().getStringExtra("from2");
        pUserAvaR = getIntent().getIntExtra("from3", R.mipmap.bg_me_press); //default bg_me_press
        ((TextView)findViewById(R.id.text_view_from_user_name)).setText(pUserName);

        imageViewRecording  = findViewById(R.id.image_view_voice_recording_anim);
        aListView           = findViewById(R.id.list_view_message);

        mBtnStart = findViewById(R.id.button_start);
        mBtnStop  = findViewById(R.id.button_stop);

        aList    = new ArrayList<>(Arrays.asList(new AMessage("大家说，我听，您看。", "800", "你说", R.mipmap.default_user_avatar, false)));
        aAdapter = new MyListViewAdapter(ChatListView.this, aList);
        aListView.setAdapter(aAdapter);

        asrListener  = new ASRListener();
        asrRecognizer= MLAsrRecognizer.createAsrRecognizer(this);    //a 用户调用接口创建一个语音识别器。
        asrRecognizer.setAsrListener(asrListener);                        //b 绑定个listener
        mIntent = new Intent(MLAsrConstants.ACTION_HMS_ASR_SPEECH);
        mIntent.putExtra(MLAsrConstants.LANGUAGE, "zh-CN").putExtra(MLAsrConstants.FEATURE, MLAsrConstants.FEATURE_WORDFLUX);

        //permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED){
            Log.e(TAG, "permission granted.");
        }else if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)) {
            showExplanation(getString(R.string.request_permission_title), getString(R.string.permission_rationale_record), Manifest.permission.RECORD_AUDIO);
        } else {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
        }

    }

    private void showExplanation(String title, String message, final String permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO);
            }
        });
        builder.create().show();
    }

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Toast.makeText(this, getString(R.string.request_permission_granted), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.request_permission_record_audio), Toast.LENGTH_LONG).show();
            finish();
        }
    });



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_start) {
            asrRecognizer.startRecognizing(mIntent);
            clickStop = false;
            animationRecording = (AnimationDrawable) imageViewRecording.getDrawable();
            animationRecording.start();
            ((TextView)findViewById(R.id.text_view_recording)).setText(getString(R.string.tv_click_to_stop));
            findViewById(R.id.button_stop).setVisibility(View.VISIBLE);
            findViewById(R.id.button_start).setVisibility(View.INVISIBLE);
        }else if (view.getId() == R.id.button_stop) {
            clickStop = true;
            if (animationRecording != null && animationRecording.isRunning()) {animationRecording.stop();}
            ((TextView)findViewById(R.id.text_view_recording)).setText(getString(R.string.asr_start_recording));
            findViewById(R.id.button_stop).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_start).setVisibility(View.VISIBLE);
        }
    }

    protected class ASRListener implements MLAsrListener {ArrayList<String> listResults = new ArrayList<>();
        // 从MLAsrRecognizer接收到持续语音识别的文本，该接口并非运行在主线程中，返回结果需要在子线程中处理。
        // Bundle中携带了识别后的文本信息，文本信息以String类型保存在以MLAsrRecognizer.RESULTS_RECOGNIZING为key的value中。
        @Override
        public void onRecognizingResults(Bundle bundleResults) {
            if (bundleResults != null) {
                listResults.clear();
                listResults.add(bundleResults.getString(MLAsrRecognizer.RESULTS_RECOGNIZING));
                if (listResults != null && listResults.size() > 0) {
                    AMessage aMsg = new AMessage(listResults.get(0), pUserId, pUserName, pUserAvaR, true);
                    if (firstWord) {
                        aList.add(aMsg);
                        firstWord = false;
                    }else {
                        aList.set(aList.size() - 1, aMsg);
                    }
                    aAdapter.notifyDataSetChanged();           // refresh ListView when new messages coming
                    aListView.setSelection(aList.size());       // go to the end of the ListView
                }
                Log.e("Chat_ListView>>>>>>", "onRecognizingResults 53 " + bundleResults);    //Bundle[{results_recognizing=一颗}]
            }
        }

        @Override // 收尾。语音识别的文本数据，该接口并非运行在主线程中，返回结果需要在子线程中处理。
        public void onResults(Bundle results) {
            firstWord = true;       //本局结束。另起一行。
            if (!clickStop) {
                vad();
            }
        }

        public void vad(){
            if (mRecorder==null) {
                mMinBufferSize = AudioRecord.getMinBufferSize(16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
                mRecorder = new AudioRecord(MediaRecorder.AudioSource.MIC, 16000, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, mMinBufferSize * 2);
            }

            int readSize;
            mAudioSize = 320;
            short[] audioData = new short[mAudioSize];

            if (mRecorder.getState() != AudioRecord.STATE_INITIALIZED) {
                mRecorder.stop();
                return;
            }
            mRecorder.startRecording();

            mStartVAD = true;    //开始检测声音
            while (mStartVAD) {
                if (null != mRecorder) {
                    readSize = mRecorder.read(audioData, 0, mAudioSize);

                    if (readSize == AudioRecord.ERROR_INVALID_OPERATION || readSize == AudioRecord.ERROR_BAD_VALUE) {                        continue;                    }
                    if (readSize != 0 && readSize != -1) {
                        // 语音活动检测
                        mSpeaking = webRtcVad_Process(audioData, 0, readSize);
                        if (mSpeaking) {
                            Log.e("TAG", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>正在讲话");
                            mStartVAD = false;      //停止检测
                            asr();
                        } else {
                            Log.e("TAG", "=====当前无声音");
                        }
                    } else {
                        break;
                    }
                }
            }
        }

        public void asr(){
            if (asrRecognizer!=null) {
                asrRecognizer.destroy();
                asrRecognizer=null;
                asrRecognizer= MLAsrRecognizer.createAsrRecognizer(ChatListView.this);    //a 用户调用接口创建一个语音识别器。
                asrRecognizer.setAsrListener(asrListener);                        //b 绑定个listener
            }
            asrRecognizer.startRecognizing(mIntent);
            Log.e("Chat_ListView>>>3>>>", asrRecognizer.toString()+" listener " + asrListener.toString());
        }

        @Override //Log.e(">>>>>>", "onVoiceDataReceived-- data.length=" + length);
        public void onVoiceDataReceived(byte[] data, float energy, Bundle bundle) {
            int length = data == null ? 0 : data.length;
            // Log.d(">  >   >>>>", "onVoiceDataReceived-- energy =" + energy);
        }

        @Override // If you don't add this, there will be no response after you cut the network
        public void onError(int error, String errorMessage) {Snackbar.make(imageViewRecording, getString(R.string.no_internet), Snackbar.LENGTH_SHORT);}

        @Override
        public void onState(int state, Bundle params) {
            if (state == MLAsrConstants.STATE_NO_SOUND_TIMES_EXCEED) {
                Log.e("Chat_ListView>>>>>>", "onState: 6s内没有检测到结果3:" + state + " total6s: ");
            }else if(state == MLAsrConstants.STATE_NO_SOUND) {
                Log.e("Chat_ListView>>>>>>", "onState: 3s内没有检测到没有说话2:" + state);
            }else if(state == MLAsrConstants.STATE_LISTENING) {
                Log.e("Chat_ListView>>>>>>", "onState: 录音机已经准备好1:" + state);
            }else{
                Log.e("Chat_ListView>>>>>>", "onState: 其他:" + state);
            }
        }

        @Override   //4
        public void onStartListening() {
            Log.e("Chat_ListView>>>>>>", "3 录音器开始接收声音。onStartListening--"+ "   #" );
        }

        @Override
        public void onStartingOfSpeech() {
            Log.e("Chat_ListView>>>>>>", "5 用户开始讲话，即语音识别器检测到用户开始讲话。 onStartingOfSpeech--");
        }
    }

    public void back(View view) {finish();}       // finish() will invoke onPause();

    @Override
    protected void onPause() {              // backend run, stop ASR
        super.onPause();
        Log.e("onPause", "---------");
        stopASR();
    }

    void stopASR(){
        clickStop = true;
        findViewById(R.id.button_stop).setVisibility(View.INVISIBLE);
        findViewById(R.id.button_start).setVisibility(View.VISIBLE);
        if (animationRecording != null && animationRecording.isRunning()) {animationRecording.stop();}
        ((TextView)findViewById(R.id.text_view_recording)).setText(getString(R.string.asr_back));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopASR();
    }

    @Override
    protected void onResume() {        super.onResume();    }

    public native boolean webRtcVad_Process(short[] audioData, int offsetInshort, int readSize);

}