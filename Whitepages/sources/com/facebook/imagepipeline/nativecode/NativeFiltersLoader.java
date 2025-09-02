package com.facebook.imagepipeline.nativecode;

import com.facebook.soloader.nativeloader.NativeLoader;

public abstract class NativeFiltersLoader {
    public static void load() {
        NativeLoader.loadLibrary("native-filters");
    }
}
