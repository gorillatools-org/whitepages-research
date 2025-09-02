package com.google.firebase.perf.logging;

import java.util.Locale;

public class AndroidLogger {
    private static volatile AndroidLogger instance;
    private boolean isLogcatEnabled;
    private final LogWrapper logWrapper;

    public static AndroidLogger getInstance() {
        if (instance == null) {
            synchronized (AndroidLogger.class) {
                try {
                    if (instance == null) {
                        instance = new AndroidLogger();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return instance;
    }

    public AndroidLogger(LogWrapper logWrapper2) {
        this.isLogcatEnabled = false;
        this.logWrapper = logWrapper2 == null ? LogWrapper.getInstance() : logWrapper2;
    }

    private AndroidLogger() {
        this((LogWrapper) null);
    }

    public void setLogcatEnabled(boolean z) {
        this.isLogcatEnabled = z;
    }

    public boolean isLogcatEnabled() {
        return this.isLogcatEnabled;
    }

    public void debug(String str) {
        if (this.isLogcatEnabled) {
            this.logWrapper.d(str);
        }
    }

    public void debug(String str, Object... objArr) {
        if (this.isLogcatEnabled) {
            this.logWrapper.d(String.format(Locale.ENGLISH, str, objArr));
        }
    }

    public void verbose(String str) {
        if (this.isLogcatEnabled) {
            this.logWrapper.v(str);
        }
    }

    public void verbose(String str, Object... objArr) {
        if (this.isLogcatEnabled) {
            this.logWrapper.v(String.format(Locale.ENGLISH, str, objArr));
        }
    }

    public void info(String str) {
        if (this.isLogcatEnabled) {
            this.logWrapper.i(str);
        }
    }

    public void info(String str, Object... objArr) {
        if (this.isLogcatEnabled) {
            this.logWrapper.i(String.format(Locale.ENGLISH, str, objArr));
        }
    }

    public void warn(String str) {
        if (this.isLogcatEnabled) {
            this.logWrapper.w(str);
        }
    }

    public void warn(String str, Object... objArr) {
        if (this.isLogcatEnabled) {
            this.logWrapper.w(String.format(Locale.ENGLISH, str, objArr));
        }
    }

    public void error(String str) {
        if (this.isLogcatEnabled) {
            this.logWrapper.e(str);
        }
    }

    public void error(String str, Object... objArr) {
        if (this.isLogcatEnabled) {
            this.logWrapper.e(String.format(Locale.ENGLISH, str, objArr));
        }
    }
}
