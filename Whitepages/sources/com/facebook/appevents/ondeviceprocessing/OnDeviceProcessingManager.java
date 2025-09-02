package com.facebook.appevents.ondeviceprocessing;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEvent;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

public final class OnDeviceProcessingManager {
    private static final Set ALLOWED_IMPLICIT_EVENTS = SetsKt.setOf("fb_mobile_purchase", "StartTrial", "Subscribe");
    public static final OnDeviceProcessingManager INSTANCE = new OnDeviceProcessingManager();

    private OnDeviceProcessingManager() {
    }

    public static final boolean isOnDeviceProcessingEnabled() {
        Class<OnDeviceProcessingManager> cls = OnDeviceProcessingManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            if (FacebookSdk.getLimitEventAndDataUsage(FacebookSdk.getApplicationContext()) || Utility.isDataProcessingRestricted() || !RemoteServiceWrapper.isServiceAvailable()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final void sendInstallEventAsync(String str, String str2) {
        Class<OnDeviceProcessingManager> cls = OnDeviceProcessingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Context applicationContext = FacebookSdk.getApplicationContext();
                if (applicationContext != null && str != null && str2 != null) {
                    FacebookSdk.getExecutor().execute(new OnDeviceProcessingManager$$ExternalSyntheticLambda1(applicationContext, str2, str));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void sendInstallEventAsync$lambda$0(Context context, String str, String str2) {
        Class<OnDeviceProcessingManager> cls = OnDeviceProcessingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(context, "$context");
                SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
                String str3 = str2 + "pingForOnDevice";
                if (sharedPreferences.getLong(str3, 0) == 0) {
                    RemoteServiceWrapper.sendInstallEvent(str2);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putLong(str3, System.currentTimeMillis());
                    edit.apply();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void sendCustomEventAsync(String str, AppEvent appEvent) {
        Class<OnDeviceProcessingManager> cls = OnDeviceProcessingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "applicationId");
                Intrinsics.checkNotNullParameter(appEvent, "event");
                if (INSTANCE.isEventEligibleForOnDeviceProcessing(appEvent)) {
                    FacebookSdk.getExecutor().execute(new OnDeviceProcessingManager$$ExternalSyntheticLambda0(str, appEvent));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void sendCustomEventAsync$lambda$1(String str, AppEvent appEvent) {
        Class<OnDeviceProcessingManager> cls = OnDeviceProcessingManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$applicationId");
                Intrinsics.checkNotNullParameter(appEvent, "$event");
                RemoteServiceWrapper.sendCustomEvents(str, CollectionsKt.listOf(appEvent));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final boolean isEventEligibleForOnDeviceProcessing(AppEvent appEvent) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            boolean z = appEvent.isImplicit() && ALLOWED_IMPLICIT_EVENTS.contains(appEvent.getName());
            if (!appEvent.isImplicit() || z) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
