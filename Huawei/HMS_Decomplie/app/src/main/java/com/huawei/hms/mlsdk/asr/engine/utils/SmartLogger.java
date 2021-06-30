//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.huawei.hms.mlsdk.asr.engine.utils;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.hms.mlsdk.asr.engine.annotation.KeepASR;
import java.util.regex.Pattern;

@KeepASR
public class SmartLogger {
    private static final Pattern M_PATTERN = Pattern.compile("[0-9]*[a-z|A-Z]*[一-龥]*");
    private static final char STAR = '*';
    private static final int LEN_CONST = 2;
    public static final String TAG = "MLASR_";

    public SmartLogger() {
    }

    private static String getLogMsg(String var0, boolean var1) {
        StringBuilder var2;
        var2 = new StringBuilder.<init>(512);
        if (!TextUtils.isEmpty(var0)) {
            if (var1) {
                var2.append(formatLogWithStar(var0));
            } else {
                var2.append(var0);
            }
        }

        return var2.toString();
    }

    private static String getLogMsg(String var0, String var1) {
        StringBuilder var2;
        var2 = new StringBuilder.<init>(512);
        if (!TextUtils.isEmpty(var0)) {
            var2.append(var0);
        }

        if (!TextUtils.isEmpty(var1)) {
            var2.append(formatLogWithStar(var1));
        }

        return var2.toString();
    }

    public static void d(String var0, String var1, boolean var2) {
    }

    public static void d(String var0, String var1, String var2) {
    }

    public static void d(String var0, String var1, String var2, Throwable var3) {
    }

    public static void d(String var0, String var1) {
    }

    public static void d(String var0, String var1, Throwable var2, boolean var3) {
    }

    public static void d(String var0, String var1, Throwable var2) {
    }

    public static void i(String var0, String var1, boolean var2) {
        if (!TextUtils.isEmpty(var1)) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void i(String var0, String var1, String var2) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void i(String var0, String var1, String var2, Throwable var3) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2), getNewThrowable(var3));
        }

    }

    public static void i(String var0, String var1) {
        if (!TextUtils.isEmpty(var1)) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false));
        }

    }

    public static void i(String var0, String var1, Throwable var2, boolean var3) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var3), getNewThrowable(var2));
        }

    }

    public static void i(String var0, String var1, Throwable var2) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.i(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false), getNewThrowable(var2));
        }

    }

    public static void w(String var0, String var1, boolean var2) {
        if (!TextUtils.isEmpty(var1)) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void w(String var0, String var1, String var2) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void w(String var0, String var1, String var2, Throwable var3) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2), getNewThrowable(var3));
        }

    }

    public static void w(String var0, String var1) {
        if (!TextUtils.isEmpty(var1)) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false));
        }

    }

    public static void w(String var0, String var1, Throwable var2, boolean var3) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var3), getNewThrowable(var2));
        }

    }

    public static void w(String var0, String var1, Throwable var2) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.w(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false), getNewThrowable(var2));
        }

    }

    public static void e(String var0, String var1, boolean var2) {
        if (!TextUtils.isEmpty(var1)) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void e(String var0, String var1, String var2) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2));
        }

    }

    public static void e(String var0, String var1, String var2, Throwable var3) {
        if (!TextUtils.isEmpty(var1) || !TextUtils.isEmpty(var2)) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var2), getNewThrowable(var3));
        }

    }

    public static void e(String var0, String var1) {
        if (!TextUtils.isEmpty(var1)) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false));
        }

    }

    public static void e(String var0, String var1, Throwable var2, boolean var3) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, var3), getNewThrowable(var2));
        }

    }

    public static void e(String var0, String var1, Throwable var2) {
        if (!TextUtils.isEmpty(var1) || var2 != null) {
            Log.e(com.huawei.hms.mlsdk.asr.o.a.a("MLASR_", var0), getLogMsg(var1, false), getNewThrowable(var2));
        }

    }

    private static String formatLogWithStar(String var0) {
        if (TextUtils.isEmpty(var0)) {
            return var0;
        } else {
            int var1 = var0.length();
            if (1 == var1) {
                return String.valueOf('*');
            } else {
                StringBuilder var2;
                var2 = new StringBuilder.<init>(var1);
                int var3 = 0;

                for(int var4 = 1; var3 < var1; ++var3) {
                    char var5 = var0.charAt(var3);
                    if (M_PATTERN.matcher(String.valueOf(var5)).matches()) {
                        if (var4 % 2 == 0) {
                            var5 = '*';
                        }

                        ++var4;
                    }

                    var2.append(var5);
                }

                return var2.toString();
            }
        }
    }

    private static Throwable getNewThrowable(Throwable var0) {
        if (var0 == null) {
            return null;
        } else {
            SmartLogger.a var1;
            SmartLogger.a var10001 = var1 = new SmartLogger.a;
            var1.<init>(var0);
            var1.setStackTrace(var0.getStackTrace());
            var10001.a(modifyExceptionMessage(var0.getMessage()));
            var0 = var0.getCause();

            for(SmartLogger.a var2 = var1; var0 != null; var0 = var0.getCause()) {
                var10001 = var2;
                SmartLogger.a var10002 = var2 = new SmartLogger.a;
                var2.<init>(var0);
                var2.setStackTrace(var0.getStackTrace());
                var10002.a(modifyExceptionMessage(var0.getMessage()));
                var10001.a((Throwable)var10002);
            }

            return var1;
        }
    }

    private static String modifyExceptionMessage(String var0) {
        if (TextUtils.isEmpty(var0)) {
            return var0;
        } else {
            char[] var2 = var0.toCharArray();

            for(int var1 = 0; var1 < var2.length; ++var1) {
                if (var1 % 2 == 0) {
                    var2[var1] = '*';
                }
            }

            return new String(var2);
        }
    }

    private static class a extends Throwable {
        private String a;
        private Throwable b;
        private Throwable c;

        public a(Throwable var1) {
            this.c = var1;
        }

        public Throwable getCause() {
            Throwable var1;
            if ((var1 = this.b) == this) {
                var1 = null;
            }

            return var1;
        }

        public void a(Throwable var1) {
            this.b = var1;
        }

        public String getMessage() {
            return this.a;
        }

        public void a(String var1) {
            this.a = var1;
        }

        public String toString() {
            Throwable var1;
            if ((var1 = this.c) == null) {
                return "";
            } else {
                String var2 = var1.getClass().getName();
                if (this.a != null) {
                    var2 = com.huawei.hms.mlsdk.asr.o.a.a(var2, ": ");
                    return this.a.startsWith(var2) ? this.a : com.huawei.hms.mlsdk.asr.o.a.a(var2).append(this.a).toString();
                } else {
                    return var2;
                }
            }
        }
    }
}
