package com.google.android.gms.common.stats;

import android.os.PowerManager;
import android.os.Process;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@Deprecated
public class StatsUtils {
    @KeepForSdk
    public static String getEventKey(PowerManager.WakeLock wakeLock, String str) {
        String valueOf = String.valueOf((((long) Process.myPid()) << 32) | ((long) System.identityHashCode(wakeLock)));
        if (true == TextUtils.isEmpty(str)) {
            str = "";
        }
        return String.valueOf(valueOf).concat(String.valueOf(str));
    }
}
