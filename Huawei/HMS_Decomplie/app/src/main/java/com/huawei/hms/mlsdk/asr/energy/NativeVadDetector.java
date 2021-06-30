//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.energy;

import com.huawei.hms.mlsdk.asr.energy.vo.DetectResult;
import com.huawei.hms.mlsdk.asr.energy.vo.SampleBuffer;
import com.huawei.hms.mlsdk.asr.energy.vo.VoiceDetectParams;
import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;
import com.huawei.hms.mlsdk.asr.engine.utils.SmartLogger;
import com.huawei.hms.mlsdk.asr.o.a;

public class NativeVadDetector {
    private int a = (new Object()).hashCode();

    public NativeVadDetector() {
        this.a();
    }

    public native boolean init(VoiceDetectParams var1);

    public native DetectResult detect(SampleBuffer var1);

    public native void release();

    public void a() {
        String var10000 = "ml-vadenergy";

        try {
            System.loadLibrary(var10000);
        } catch (UnsatisfiedLinkError var1) {
            SmartLogger.e("NativeVadDetector", com.huawei.hms.mlsdk.asr.o.a.a("loadLibrary e = ").append(var1.getMessage()).toString());
        }

    }

    @KeepASR
    public int getTag() {
        return this.a;
    }
}
