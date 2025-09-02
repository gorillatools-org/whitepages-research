package com.salesforce.marketingcloud.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import com.salesforce.marketingcloud.g;

@SuppressLint({"UnknownNullness"})
public final class h {
    private static String a;
    public static final String[] b = (Build.VERSION.SDK_INT >= 29 ? new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"} : new String[]{"android.permission.ACCESS_FINE_LOCATION"});

    private h() {
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = "";
        if (context == null) {
            return "";
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            a = String.format("%s : %s", new Object[]{packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)});
        } catch (PackageManager.NameNotFoundException e) {
            g.b(l.c, e, "Failed to get Application Version from the PackageManager.", new Object[0]);
        }
        return a;
    }

    public static boolean b(Context context) {
        if (context == null) {
            return false;
        }
        for (String a2 : b) {
            if (!a(context, a2)) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(Context context) {
        if (context == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            return a(context, "android.permission.BLUETOOTH_SCAN") && a(context, "android.permission.BLUETOOTH_CONNECT");
        }
        return true;
    }

    public static boolean a(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    @SuppressLint({"QueryPermissionsNeeded"})
    public static boolean b(PackageManager packageManager, Intent intent) {
        return packageManager.queryIntentServices(intent, 65536).size() > 0;
    }

    @SuppressLint({"QueryPermissionsNeeded"})
    public static boolean a(PackageManager packageManager, Intent intent) {
        return packageManager.queryBroadcastReceivers(intent, 0).size() > 0;
    }
}
