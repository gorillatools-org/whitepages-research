package com.facebook.soloader;

import com.facebook.soloader.nativeloader.NativeLoaderDelegate;

public class NativeLoaderToSoLoaderDelegate implements NativeLoaderDelegate {
    public boolean loadLibrary(String str, int i) {
        return SoLoader.loadLibrary(str, (i & 1) != 0 ? 16 : 0);
    }
}
