//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr;

import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;

@KeepASR
public class MLAsrConstants {
    public static final String FEATURE = "FEATURE";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String PUNCTUATION_ENABLE = "PUNCTUATION_ENABLE";
    public static final String SCENES = "scenes";
    public static final String SCENES_SHOPPING = "shopping";
    public static final int FEATURE_WORDFLUX = 11;
    public static final int FEATURE_ALLINONE = 12;
    public static final String ACTION_HMS_ASR_SPEECH = "com.huawei.hms.mlsdk.action.RECOGNIZE_SPEECH";
    public static final String WAVE_PAINE_ENCODING = "ENCODING";
    public static final String WAVE_PAINE_SAMPLE_RATE = "SAMPLE_RATE";
    public static final String WAVE_PAINE_BIT_WIDTH = "BIT_WIDTH";
    public static final String WAVE_PAINE_CHANNEL_COUNT = "CHANNEL_COUNT";
    public static final int STATE_LISTENING = 1;
    public static final int STATE_NO_SOUND = 2;
    public static final int STATE_NO_SOUND_TIMES_EXCEED = 3;
    public static final int STATE_NO_UNDERSTAND = 6;
    public static final int STATE_NO_NETWORK = 7;
    public static final int STATE_WAITING = 9;
    @Deprecated
    public static final String LAN_ZH = "zh";
    public static final String LAN_ZH_CN = "zh-CN";
    public static final String LAN_EN_US = "en-US";
    public static final String LAN_FR_FR = "fr-FR";
    public static final String LAN_ES_ES = "es-ES";
    public static final String LAN_EN_IN = "en-IN";
    public static final String LAN_DE_DE = "de-DE";
    public static final int ERR_NO_NETWORK = 11202;
    public static final int ERR_SERVICE_UNAVAILABLE = 11203;
    public static final int ERR_NO_UNDERSTAND = 11204;
    public static final int ERR_INVALIDATE_TOKEN = 11219;

    public MLAsrConstants() {
    }
}
