package com.facebook.appevents.suggestedevents;

import android.app.Activity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.appevents.ml.ModelManager;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

public final class SuggestedEventsManager {
    public static final SuggestedEventsManager INSTANCE = new SuggestedEventsManager();
    private static final Set eligibleEvents = new LinkedHashSet();
    private static final AtomicBoolean enabled = new AtomicBoolean(false);
    private static final Set productionEvents = new LinkedHashSet();

    private SuggestedEventsManager() {
    }

    public static final synchronized void enable() {
        synchronized (SuggestedEventsManager.class) {
            if (!CrashShieldHandler.isObjectCrashing(SuggestedEventsManager.class)) {
                try {
                    FacebookSdk.getExecutor().execute(new SuggestedEventsManager$$ExternalSyntheticLambda0());
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, SuggestedEventsManager.class);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void enable$lambda$0() {
        Class<SuggestedEventsManager> cls = SuggestedEventsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                AtomicBoolean atomicBoolean = enabled;
                if (!atomicBoolean.get()) {
                    atomicBoolean.set(true);
                    INSTANCE.initialize();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void initialize() {
        String suggestedEventsSetting;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null && (suggestedEventsSetting = queryAppSettings.getSuggestedEventsSetting()) != null) {
                    populateEventsFromRawJsonString$facebook_core_release(suggestedEventsSetting);
                    if (productionEvents.isEmpty()) {
                        if (eligibleEvents.isEmpty()) {
                            return;
                        }
                    }
                    File ruleFile = ModelManager.getRuleFile(ModelManager.Task.MTML_APP_EVENT_PREDICTION);
                    if (ruleFile != null) {
                        FeatureExtractor.initialize(ruleFile);
                        Activity currentActivity = ActivityLifecycleTracker.getCurrentActivity();
                        if (currentActivity != null) {
                            trackActivity(currentActivity);
                        }
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final void populateEventsFromRawJsonString$facebook_core_release(String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("production_events")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("production_events");
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        Set set = productionEvents;
                        String string = jSONArray.getString(i);
                        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                        set.add(string);
                    }
                }
                if (jSONObject.has("eligible_for_prediction_events")) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("eligible_for_prediction_events");
                    int length2 = jSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        Set set2 = eligibleEvents;
                        String string2 = jSONArray2.getString(i2);
                        Intrinsics.checkNotNullExpressionValue(string2, "jsonArray.getString(i)");
                        set2.add(string2);
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final void trackActivity(Activity activity) {
        Class<SuggestedEventsManager> cls = SuggestedEventsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                try {
                    if (enabled.get() && FeatureExtractor.isInitialized()) {
                        if (productionEvents.isEmpty()) {
                            if (!eligibleEvents.isEmpty()) {
                            }
                        }
                        ViewObserver.Companion.startTrackingActivity(activity);
                        return;
                    }
                    ViewObserver.Companion.stopTrackingActivity(activity);
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final boolean isProductionEvents$facebook_core_release(String str) {
        Class<SuggestedEventsManager> cls = SuggestedEventsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "event");
            return productionEvents.contains(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final boolean isEligibleEvents$facebook_core_release(String str) {
        Class<SuggestedEventsManager> cls = SuggestedEventsManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "event");
            return eligibleEvents.contains(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }
}
