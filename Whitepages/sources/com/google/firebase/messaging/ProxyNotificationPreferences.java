package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerImplCommon$$ExternalSyntheticLambda3;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.concurrent.Executor;

final class ProxyNotificationPreferences {
    private static final String FCM_PREFERENCES = "com.google.firebase.messaging";

    private ProxyNotificationPreferences() {
    }

    private static SharedPreferences getPreference(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        return context.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    static void setProxyNotificationsInitialized(Context context, boolean z) {
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putBoolean("proxy_notification_initialized", z);
        edit.apply();
    }

    static void setProxyRetention(Context context, GmsRpc gmsRpc, boolean z) {
        if (PlatformVersion.isAtLeastQ() && !isProxyNotificationRetentionSet(getPreference(context), z)) {
            gmsRpc.setRetainProxiedNotifications(z).addOnSuccessListener((Executor) new MeasurementManagerImplCommon$$ExternalSyntheticLambda3(), new ProxyNotificationPreferences$$ExternalSyntheticLambda0(context, z));
        }
    }

    static boolean isProxyNotificationInitialized(Context context) {
        return getPreference(context).getBoolean("proxy_notification_initialized", false);
    }

    static boolean isProxyNotificationRetentionSet(SharedPreferences sharedPreferences, boolean z) {
        if (!sharedPreferences.contains("proxy_retention") || sharedPreferences.getBoolean("proxy_retention", false) != z) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public static void setProxyRetentionPreferences(Context context, boolean z) {
        SharedPreferences.Editor edit = getPreference(context).edit();
        edit.putBoolean("proxy_retention", z);
        edit.apply();
    }
}
