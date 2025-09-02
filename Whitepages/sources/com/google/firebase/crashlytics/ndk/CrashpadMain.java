package com.google.firebase.crashlytics.ndk;

import android.util.Log;
import com.google.firebase.crashlytics.internal.Logger;

public class CrashpadMain {
    public static native void crashpadMain(String[] strArr);

    public static void main(String[] strArr) {
        try {
            String str = strArr[1];
            Log.d(Logger.TAG, "Path to shared objects is " + str);
            System.load(str + "libcrashlytics-handler.so");
            crashpadMain(strArr);
        } catch (UnsatisfiedLinkError e) {
            throw new RuntimeException(e);
        }
    }
}
