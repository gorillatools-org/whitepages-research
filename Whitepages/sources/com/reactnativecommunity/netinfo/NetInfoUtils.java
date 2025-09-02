package com.reactnativecommunity.netinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import androidx.core.content.ContextCompat;

public abstract class NetInfoUtils {
    public static void reverseByteArray(byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            byte b = bArr[i];
            bArr[i] = bArr[(bArr.length - i) - 1];
            bArr[(bArr.length - i) - 1] = b;
        }
    }

    public static boolean isAccessWifiStatePermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_WIFI_STATE") == 0;
    }

    public static void compatRegisterReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, boolean z) {
        if (Build.VERSION.SDK_INT < 34 || context.getApplicationInfo().targetSdkVersion < 34) {
            context.registerReceiver(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter, z ? 2 : 4);
        }
    }
}
