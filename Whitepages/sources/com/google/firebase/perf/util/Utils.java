package com.google.firebase.perf.util;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.firebase.perf.logging.AndroidLogger;
import okhttp3.HttpUrl;

public class Utils {
    private static Boolean isDebugLoggingEnabled;

    public static int saturatedIntCast(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return j < -2147483648L ? ExploreByTouchHelper.INVALID_ID : (int) j;
    }

    public static String stripSensitiveInfo(String str) {
        HttpUrl parse = HttpUrl.parse(str);
        return parse != null ? parse.newBuilder().username("").password("").query((String) null).fragment((String) null).toString() : str;
    }

    public static String truncateURL(String str, int i) {
        int lastIndexOf;
        if (str.length() <= i) {
            return str;
        }
        if (str.charAt(i) == '/') {
            return str.substring(0, i);
        }
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            return str.substring(0, i);
        }
        if (parse.encodedPath().lastIndexOf(47) < 0 || (lastIndexOf = str.lastIndexOf(47, i - 1)) < 0) {
            return str.substring(0, i);
        }
        return str.substring(0, lastIndexOf);
    }

    public static int bufferToInt(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 4 && i < bArr.length) {
            i2 |= (bArr[i] & 255) << (i * 8);
            i++;
        }
        return i2;
    }

    public static boolean isDebugLoggingEnabled(Context context) {
        Boolean bool = isDebugLoggingEnabled;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("firebase_performance_logcat_enabled", false));
            isDebugLoggingEnabled = valueOf;
            return valueOf.booleanValue();
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            AndroidLogger instance = AndroidLogger.getInstance();
            instance.debug("No perf logcat meta data found " + e.getMessage());
            return false;
        }
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}
