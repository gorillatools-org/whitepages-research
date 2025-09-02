package com.google.protobuf;

abstract class Android {
    private static boolean ASSUME_ANDROID;
    private static final boolean IS_ROBOLECTRIC = (!ASSUME_ANDROID && getClassForName("org.robolectric.Robolectric") != null);
    private static final Class MEMORY_CLASS = getClassForName("libcore.io.Memory");

    static boolean isOnAndroidDevice() {
        return ASSUME_ANDROID || (MEMORY_CLASS != null && !IS_ROBOLECTRIC);
    }

    static Class getMemoryClass() {
        return MEMORY_CLASS;
    }

    private static Class getClassForName(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
