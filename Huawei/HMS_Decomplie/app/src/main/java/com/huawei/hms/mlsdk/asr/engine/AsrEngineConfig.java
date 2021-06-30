//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.engine;

import android.os.Bundle;
import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;
import java.util.HashMap;
import java.util.Map;

@KeepASR
public class AsrEngineConfig {
    public static final int DEFAULT_VAD_START_MUTE_DURATION = 3000;
    public static final int DEFAULT_VAD_START_MUTE_DETECT_TIMES = 2;
    public static final int DEFAULT_VAD_END_MUTE_DURATION = 700;
    public static final int DEFAULT_VAD_DETECT_DURATION = 20;
    public static final int DEFAULT_RECOGNIZE_DURATION = 200;
    public static final int DEFAULT_MAX_AUDIO_DURATION = 60000;
    public static final String FEATURE = "FEATURE";
    public static final String LANGUAGE = "LANGUAGE";
    public static final String PUNCTUATION_ENABLE = "PUNCTUATION_ENABLE";
    public static final String RECOGNIZER_TYPE = "RECOGNIZER_TYPE";
    public static final int DEFAULT_FEATURE = 11;
    public static final String DEFAULT_LANGUAGE = "en-US";
    public static final String SCENES_KEY = "scenes";
    private static Map<String, String> LAN_TRANSCODE_MAP = new HashMap();
    private Integer feature;
    private String language;
    private Integer vadStartMuteDuration;
    private Integer vadStartMuteDetectTimes;
    private Integer vadEndMuteDuration;
    private Integer vadDetectDuration;
    private Integer recognizeDuration;
    private Integer maxAudioDuration;
    private Boolean enableSilenceDetection;
    private Integer silenceDurationThreshold;
    private Boolean enablePunctuation;
    private int recognizerType;
    private boolean wordTimeOffset;
    private boolean sentenceTimeOffset;
    private String scenes;

    public AsrEngineConfig() {
    }

    static {
        LAN_TRANSCODE_MAP.put("zh-CN", "zh");
        LAN_TRANSCODE_MAP.put("zh", "zh");
        LAN_TRANSCODE_MAP.put("en-US", "en-US");
        LAN_TRANSCODE_MAP.put("fr-FR", "fr");
        LAN_TRANSCODE_MAP.put("es-ES", "es");
        LAN_TRANSCODE_MAP.put("de-DE", "de");
    }

    public AsrEngineConfig load(Bundle var1) {
        AsrEngineConfig var2;
        var2 = new AsrEngineConfig.<init>();
        if (var1 == null) {
            return var2;
        } else {
            if (var1.containsKey("FEATURE")) {
                this.feature = var1.getInt("FEATURE");
                var2.setFeature(this.feature);
            }

            if (var1.containsKey("LANGUAGE")) {
                this.language = var1.getString("LANGUAGE");
                var2.setLanguage(this.language);
            }

            if (var1.containsKey("PUNCTUATION_ENABLE")) {
                this.enablePunctuation = var1.getBoolean("PUNCTUATION_ENABLE");
                var2.setEnablePunctuation(this.enablePunctuation);
            }

            if (var1.containsKey("scenes")) {
                this.scenes = var1.getString("scenes");
                var2.setScenes(this.scenes);
            }

            return var2;
        }
    }

    public int getFeature() {
        if (this.feature == null) {
            this.feature = 11;
        }

        return this.feature;
    }

    public void setFeature(int var1) {
        this.feature = var1;
    }

    public String getLanguage() {
        if (this.language == null) {
            this.language = "en-US";
        }

        return this.language;
    }

    public void setLanguage(String var1) {
        if (LAN_TRANSCODE_MAP.containsKey(var1)) {
            this.language = (String)LAN_TRANSCODE_MAP.get(var1);
        } else {
            this.language = var1;
        }
    }

    public int getVadStartMuteDuration() {
        if (this.vadStartMuteDuration == null) {
            this.vadStartMuteDuration = 3000;
        }

        return this.vadStartMuteDuration;
    }

    public void setVadStartMuteDuration(int var1) {
        this.vadStartMuteDuration = var1;
    }

    public int getVadStartMuteDetectTimes() {
        if (this.vadStartMuteDetectTimes == null) {
            this.vadStartMuteDetectTimes = 2;
        }

        return this.vadStartMuteDetectTimes;
    }

    public void setVadStartMuteDetectTimes(int var1) {
        this.vadStartMuteDetectTimes = var1;
    }

    public int getVadDetectDuration() {
        if (this.vadDetectDuration == null) {
            this.vadDetectDuration = 20;
        }

        return this.vadDetectDuration;
    }

    public void setVadDetectDuration(int var1) {
        this.vadDetectDuration = var1;
    }

    public int getVadEndMuteDuration() {
        if (this.vadEndMuteDuration == null) {
            this.vadEndMuteDuration = 700;
        }

        return this.vadEndMuteDuration;
    }

    public void setVadEndMuteDuration(int var1) {
        this.vadEndMuteDuration = var1;
    }

    public int getRecognizeDuration() {
        if (this.recognizeDuration == null) {
            this.recognizeDuration = 200;
        }

        return this.recognizeDuration;
    }

    public void setRecognizeDuration(int var1) {
        this.recognizeDuration = var1;
    }

    public int getMaxAudioDuration() {
        if (this.maxAudioDuration == null) {
            this.maxAudioDuration = 60000;
        }

        return this.maxAudioDuration;
    }

    public void setMaxAudioDuration(int var1) {
        this.maxAudioDuration = var1;
    }

    public Boolean getEnableSilenceDetection() {
        return this.enableSilenceDetection;
    }

    public void setEnableSilenceDetection(Boolean var1) {
        this.enableSilenceDetection = var1;
    }

    public Integer getSilenceDurationThreshold() {
        return this.silenceDurationThreshold;
    }

    public void setSilenceDurationThreshold(Integer var1) {
        this.silenceDurationThreshold = var1;
    }

    public Boolean getEnablePunctuation() {
        return this.enablePunctuation;
    }

    public void setEnablePunctuation(Boolean var1) {
        this.enablePunctuation = var1;
    }

    public int getRecognizerType() {
        return this.recognizerType;
    }

    public void setRecognizerType(int var1) {
        this.recognizerType = var1;
    }

    public boolean isWordTimeOffset() {
        return this.wordTimeOffset;
    }

    public void setWordTimeOffset(boolean var1) {
        this.wordTimeOffset = var1;
    }

    public boolean isSentenceTimeOffset() {
        return this.sentenceTimeOffset;
    }

    public void setSentenceTimeOffset(boolean var1) {
        this.sentenceTimeOffset = var1;
    }

    public String getScenes() {
        return this.scenes;
    }

    public void setScenes(String var1) {
        this.scenes = var1;
    }
}
