//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.energy.vo;

import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;

@KeepASR
public class VoiceDetectParams {
    private int voiceVarianceThres;
    private int currAveAmpThd;
    private int smoothAmpThd;
    private int smoothUpdateThd;
    private int smoothFrmAlpha;
    private int currFrmAlpha;
    private int beta;
    private int artiSpeechThd;
    private int chopNumThd;
    private int blockVadEnable;

    private VoiceDetectParams(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, int var10) {
        this.voiceVarianceThres = var1;
        this.currAveAmpThd = var2;
        this.smoothAmpThd = var3;
        this.smoothUpdateThd = var4;
        this.smoothFrmAlpha = var5;
        this.currFrmAlpha = var6;
        this.beta = var7;
        this.artiSpeechThd = var8;
        this.chopNumThd = var9;
        this.blockVadEnable = var10;
    }

    public int getVoiceVarianceThres() {
        return this.voiceVarianceThres;
    }

    public int getCurrAveAmpThd() {
        return this.currAveAmpThd;
    }

    public int getSmoothAmpThd() {
        return this.smoothAmpThd;
    }

    public int getSmoothUpdateThd() {
        return this.smoothUpdateThd;
    }

    public int getSmoothFrmAlpha() {
        return this.smoothFrmAlpha;
    }

    public int getCurrFrmAlpha() {
        return this.currFrmAlpha;
    }

    public int getBeta() {
        return this.beta;
    }

    public int getArtiSpeechThd() {
        return this.artiSpeechThd;
    }

    public int getChopNumThd() {
        return this.chopNumThd;
    }

    public int getBlockVadEnable() {
        return this.blockVadEnable;
    }

    public static final class a {
        private int a = 0;
        private int b = 300;
        private int c = 200;
        private int d = 300;
        private int e = 22937;
        private int f = 9830;
        private int g = 12;
        private int h = 300;
        private int i = 8;
        private int j = 0;

        public a() {
        }

        public VoiceDetectParams.a a(int var1) {
            this.h = var1;
            return this;
        }

        public VoiceDetectParams a() {
            return new VoiceDetectParams(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, (b)null);
        }
    }
}
