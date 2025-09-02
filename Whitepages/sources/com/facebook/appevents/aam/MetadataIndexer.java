package com.facebook.appevents.aam;

import android.app.Activity;
import com.facebook.FacebookSdk;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import kotlin.jvm.internal.Intrinsics;

public final class MetadataIndexer {
    public static final MetadataIndexer INSTANCE = new MetadataIndexer();
    private static final String TAG = MetadataIndexer.class.getCanonicalName();
    private static boolean enabled;

    private MetadataIndexer() {
    }

    public static final void onActivityResumed(Activity activity) {
        Class<MetadataIndexer> cls = MetadataIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                try {
                    if (!enabled) {
                        return;
                    }
                    if (!MetadataRule.Companion.getRules().isEmpty()) {
                        MetadataViewObserver.Companion.startTrackingActivity(activity);
                    }
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void updateRules() {
        String rawAamRules;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null && (rawAamRules = queryAppSettings.getRawAamRules()) != null) {
                    MetadataRule.Companion.updateRules(rawAamRules);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final void enable() {
        Class<MetadataIndexer> cls = MetadataIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                FacebookSdk.getExecutor().execute(new MetadataIndexer$$ExternalSyntheticLambda0());
            } catch (Exception e) {
                Utility.logd(TAG, e);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void enable$lambda$0() {
        Class<MetadataIndexer> cls = MetadataIndexer.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!AttributionIdentifiers.Companion.isTrackingLimited(FacebookSdk.getApplicationContext())) {
                    INSTANCE.updateRules();
                    enabled = true;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
