//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr;

import android.os.Bundle;
import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;

@KeepASR
public interface MLAsrListener {
    void onResults(Bundle var1);

    void onRecognizingResults(Bundle var1);

    void onError(int var1, String var2);

    void onStartListening();

    void onStartingOfSpeech();

    void onVoiceDataReceived(byte[] var1, float var2, Bundle var3);

    void onState(int var1, Bundle var2);
}
