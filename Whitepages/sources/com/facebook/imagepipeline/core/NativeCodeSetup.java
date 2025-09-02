package com.facebook.imagepipeline.core;

public abstract class NativeCodeSetup {
    private static boolean sUseNativeCode = true;

    public static void setUseNativeCode(boolean z) {
        sUseNativeCode = z;
    }

    public static boolean getUseNativeCode() {
        return sUseNativeCode;
    }
}
