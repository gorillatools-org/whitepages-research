package com.facebook.react.devsupport;

import com.facebook.soloader.SoLoader;

public final class DevSupportSoLoader {
    public static final DevSupportSoLoader INSTANCE = new DevSupportSoLoader();
    private static volatile boolean didInit;

    private DevSupportSoLoader() {
    }

    public static final synchronized void staticInit() {
        synchronized (DevSupportSoLoader.class) {
            if (!didInit) {
                SoLoader.loadLibrary("react_devsupportjni");
                didInit = true;
            }
        }
    }
}
