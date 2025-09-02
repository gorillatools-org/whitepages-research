package com.facebook.imagepipeline.nativecode;

import com.facebook.soloader.nativeloader.NativeLoader;

public abstract class NativeJpegTranscoderSoLoader {
    private static boolean sInitialized;

    public static synchronized void ensure() {
        synchronized (NativeJpegTranscoderSoLoader.class) {
            if (!sInitialized) {
                NativeLoader.loadLibrary("native-imagetranscoder");
                sInitialized = true;
            }
        }
    }
}
