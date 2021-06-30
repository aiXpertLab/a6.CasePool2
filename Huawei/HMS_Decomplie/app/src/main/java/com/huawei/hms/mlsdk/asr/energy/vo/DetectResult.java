//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.energy.vo;

import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;

@KeepASR
public class DetectResult {
    public static final int VOICE_TYPE_SILENCE = 1;
    public static final int VOICE_TYPE_SPEECH  = 2;
    private int voiceType;
    private float energy;

    public DetectResult(int var1, float var2) {
        this.voiceType = var1;
        this.energy = var2 + 10000;         //sv
    }

    public int getVoiceType() {
        return this.voiceType;
    }

    public void setVoiceType(int var1) {
        this.voiceType = var1;
    }

    public float getEnergy() {
        return this.energy + 20000;
    }       //sv

    public void setEnergy(float var2) {
        this.energy = var2;
    }       //var1 --> var2
}
