package com.salesforce.marketingcloud.proximity;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.MCKeep;
import com.salesforce.marketingcloud.g;
import org.altbeacon.beacon.logging.Logger;

@SuppressLint({"UnknownNullness"})
@MCKeep
public final class BeaconLogger implements Logger {
    private static final String TAG = g.a("BeaconLogger");

    public void d(String str, String str2, Object... objArr) {
        g.a(TAG, str2, objArr);
    }

    public void e(String str, String str2, Object... objArr) {
        g.b(TAG, str2, objArr);
    }

    public void i(String str, String str2, Object... objArr) {
        g.c(TAG, str2, objArr);
    }

    public void v(String str, String str2, Object... objArr) {
        g.d(TAG, str2, objArr);
    }

    public void w(String str, String str2, Object... objArr) {
        g.e(TAG, str2, objArr);
    }

    public void d(Throwable th, String str, String str2, Object... objArr) {
        g.a(TAG, th, str2, objArr);
    }

    public void e(Throwable th, String str, String str2, Object... objArr) {
        g.b(TAG, th, str2, objArr);
    }

    public void i(Throwable th, String str, String str2, Object... objArr) {
        g.c(TAG, th, str2, objArr);
    }

    public void v(Throwable th, String str, String str2, Object... objArr) {
        g.d(TAG, th, str2, objArr);
    }

    public void w(Throwable th, String str, String str2, Object... objArr) {
        g.e(TAG, th, str2, objArr);
    }
}
