//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.engine;

import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;

@KeepASR
public class AsrConstants {
    public AsrConstants() {
    }

    @KeepASR
    public interface AsrGrs {
        String ASR_SHORT_URL = "/v1/rasr/short";
        String ASR_LONG_URL = "/v1/rasr/long";
        String ASR_SHORT_QUERY_LANGUAGE_URL = "/v1/asr/languages?serviceInterface=ASR_SHORT";
        String ASR_LONG_QUERY_LANGUAGE_URL = "/v1/asr/languages?serviceInterface=ASR_LONG";
    }

    @KeepASR
    public interface RecognizerType {
        int RECOGNIZER_SHORT = 0;
        int RECOGNIZER_LONG = 1;
    }

    @KeepASR
    public interface State {
        int LISTENING = 1;
        int NO_SOUND = 2;
        int NO_SOUND_TIMES_EXCEED = 3;
        int MAX_DURATION_EXCEED = 4;
        int RECOGNIZING = 5;
        int NO_UNDERSTAND = 6;
        int NO_NETWORK = 7;
        int OK_FINISHED = 8;
        int WAITING = 9;
        int STARTING_SPEECH = 10;
        int SERVICE_UNAVAILABLE = 40;
        int ABNORMAL_INTERUPTED = 41;
        int SERVICE_RECONNECTING = 42;
        int SERVICE_RECONNECTED = 43;
        int INVALIDATE_TOKEN = 44;
    }
}
