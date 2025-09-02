package app.notifee.core.event;

import app.notifee.core.KeepForSdk;

@KeepForSdk
public class LogEvent {
    @KeepForSdk
    public static final String LEVEL_DEBUG = "debug";
    @KeepForSdk
    public static final String LEVEL_ERROR = "error";
    @KeepForSdk
    public static final String LEVEL_INFO = "info";
    @KeepForSdk
    public static final String LEVEL_VERBOSE = "verbose";
    @KeepForSdk
    public static final String LEVEL_WARN = "warn";
    public final String a;
    public final String b;
    public final String c;
    public final Throwable d;

    public LogEvent(String str, String str2, String str3) {
        this.a = str2;
        this.b = str;
        this.c = str3;
        this.d = null;
    }

    @KeepForSdk
    public String getLevel() {
        return this.b;
    }

    @KeepForSdk
    public String getMessage() {
        return this.c;
    }

    @KeepForSdk
    public String getTag() {
        return this.a;
    }

    @KeepForSdk
    public Throwable getThrowable() {
        return this.d;
    }

    public LogEvent(String str, String str2, String str3, Throwable th) {
        this.a = str2;
        this.b = str;
        this.c = str3;
        this.d = th;
    }
}
