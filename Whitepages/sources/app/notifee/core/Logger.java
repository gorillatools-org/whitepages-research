package app.notifee.core;

import android.util.Log;
import app.notifee.core.event.LogEvent;
import n.o.t.i.f.e.e.f;

public class Logger {
    public static String a(String str, String str2) {
        return "(" + str + "): " + str2;
    }

    @KeepForSdk
    public static void d(String str, String str2) {
        Log.d("NOTIFEE", a(str, str2));
    }

    @KeepForSdk
    public static void e(String str, String str2, Exception exc) {
        Log.e("NOTIFEE", a(str, str2), exc);
        f.a(new LogEvent("error", str, str2, exc));
    }

    @KeepForSdk
    public static void i(String str, String str2) {
        Log.i("NOTIFEE", a(str, str2));
    }

    @KeepForSdk
    public static void v(String str, String str2) {
        Log.v("NOTIFEE", a(str, str2));
    }

    @KeepForSdk
    public static void w(String str, String str2) {
        Log.w("NOTIFEE", a(str, str2));
    }

    @KeepForSdk
    public static void e(String str, String str2) {
        Log.e("NOTIFEE", a(str, str2));
        f.a(new LogEvent("error", str, str2));
    }

    @KeepForSdk
    public static void e(String str, String str2, Throwable th) {
        Log.e("NOTIFEE", a(str, str2), th);
        f.a(new LogEvent("error", str, str2, th));
    }
}
