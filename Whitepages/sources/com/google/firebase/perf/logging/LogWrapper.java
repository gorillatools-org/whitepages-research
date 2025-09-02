package com.google.firebase.perf.logging;

import android.util.Log;

class LogWrapper {
    private static final String LOG_TAG = "FirebasePerformance";
    private static LogWrapper instance;

    public static synchronized LogWrapper getInstance() {
        LogWrapper logWrapper;
        synchronized (LogWrapper.class) {
            try {
                if (instance == null) {
                    instance = new LogWrapper();
                }
                logWrapper = instance;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        return logWrapper;
    }

    /* access modifiers changed from: package-private */
    public void d(String str) {
        Log.d(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void v(String str) {
        Log.v(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void i(String str) {
        Log.i(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void w(String str) {
        Log.w(LOG_TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void e(String str) {
        Log.e(LOG_TAG, str);
    }

    private LogWrapper() {
    }
}
