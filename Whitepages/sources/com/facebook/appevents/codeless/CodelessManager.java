package com.facebook.appevents.codeless;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.internal.AppEventUtility;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONObject;

public final class CodelessManager {
    public static final CodelessManager INSTANCE = new CodelessManager();
    private static String deviceSessionID;
    private static final AtomicBoolean isAppIndexingEnabled = new AtomicBoolean(false);
    private static volatile boolean isCheckingSession;
    private static final AtomicBoolean isCodelessEnabled = new AtomicBoolean(true);
    private static SensorManager sensorManager;
    private static ViewIndexer viewIndexer;
    private static final ViewIndexingTrigger viewIndexingTrigger = new ViewIndexingTrigger();

    private final boolean isDebugOnEmulator() {
        CrashShieldHandler.isObjectCrashing(this);
        return false;
    }

    private CodelessManager() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (INSTANCE.isDebugOnEmulator() != false) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void onActivityResumed(android.app.Activity r7) {
        /*
            java.lang.Class<com.facebook.appevents.codeless.CodelessManager> r0 = com.facebook.appevents.codeless.CodelessManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            if (r1 == 0) goto L_0x0009
            return
        L_0x0009:
            java.lang.String r1 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)     // Catch:{ all -> 0x0036 }
            java.util.concurrent.atomic.AtomicBoolean r1 = isCodelessEnabled     // Catch:{ all -> 0x0036 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0017
            return
        L_0x0017:
            com.facebook.appevents.codeless.CodelessMatcher$Companion r1 = com.facebook.appevents.codeless.CodelessMatcher.Companion     // Catch:{ all -> 0x0036 }
            com.facebook.appevents.codeless.CodelessMatcher r1 = r1.getInstance()     // Catch:{ all -> 0x0036 }
            r1.add(r7)     // Catch:{ all -> 0x0036 }
            android.content.Context r1 = r7.getApplicationContext()     // Catch:{ all -> 0x0036 }
            java.lang.String r2 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ all -> 0x0036 }
            com.facebook.internal.FetchedAppSettings r3 = com.facebook.internal.FetchedAppSettingsManager.getAppSettingsWithoutQuery(r2)     // Catch:{ all -> 0x0036 }
            r4 = 1
            if (r3 == 0) goto L_0x0038
            boolean r5 = r3.getCodelessEventsEnabled()     // Catch:{ all -> 0x0036 }
            if (r5 != r4) goto L_0x0038
            goto L_0x0040
        L_0x0036:
            r7 = move-exception
            goto L_0x0085
        L_0x0038:
            com.facebook.appevents.codeless.CodelessManager r5 = INSTANCE     // Catch:{ all -> 0x0036 }
            boolean r5 = r5.isDebugOnEmulator()     // Catch:{ all -> 0x0036 }
            if (r5 == 0) goto L_0x0071
        L_0x0040:
            java.lang.String r5 = "sensor"
            java.lang.Object r1 = r1.getSystemService(r5)     // Catch:{ all -> 0x0036 }
            android.hardware.SensorManager r1 = (android.hardware.SensorManager) r1     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x004b
            return
        L_0x004b:
            sensorManager = r1     // Catch:{ all -> 0x0036 }
            android.hardware.Sensor r4 = r1.getDefaultSensor(r4)     // Catch:{ all -> 0x0036 }
            com.facebook.appevents.codeless.ViewIndexer r5 = new com.facebook.appevents.codeless.ViewIndexer     // Catch:{ all -> 0x0036 }
            r5.<init>(r7)     // Catch:{ all -> 0x0036 }
            viewIndexer = r5     // Catch:{ all -> 0x0036 }
            com.facebook.appevents.codeless.ViewIndexingTrigger r7 = viewIndexingTrigger     // Catch:{ all -> 0x0036 }
            com.facebook.appevents.codeless.CodelessManager$$ExternalSyntheticLambda0 r6 = new com.facebook.appevents.codeless.CodelessManager$$ExternalSyntheticLambda0     // Catch:{ all -> 0x0036 }
            r6.<init>(r3, r2)     // Catch:{ all -> 0x0036 }
            r7.setOnShakeListener(r6)     // Catch:{ all -> 0x0036 }
            r6 = 2
            r1.registerListener(r7, r4, r6)     // Catch:{ all -> 0x0036 }
            if (r3 == 0) goto L_0x0071
            boolean r7 = r3.getCodelessEventsEnabled()     // Catch:{ all -> 0x0036 }
            if (r7 == 0) goto L_0x0071
            r5.schedule()     // Catch:{ all -> 0x0036 }
        L_0x0071:
            com.facebook.appevents.codeless.CodelessManager r7 = INSTANCE     // Catch:{ all -> 0x0036 }
            boolean r1 = r7.isDebugOnEmulator()     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x0084
            java.util.concurrent.atomic.AtomicBoolean r1 = isAppIndexingEnabled     // Catch:{ all -> 0x0036 }
            boolean r1 = r1.get()     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0084
            r7.checkCodelessSession(r2)     // Catch:{ all -> 0x0036 }
        L_0x0084:
            return
        L_0x0085:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.codeless.CodelessManager.onActivityResumed(android.app.Activity):void");
    }

    /* access modifiers changed from: private */
    public static final void onActivityResumed$lambda$0(FetchedAppSettings fetchedAppSettings, String str) {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$appId");
                boolean z = fetchedAppSettings != null && fetchedAppSettings.getCodelessEventsEnabled();
                boolean codelessSetupEnabled = FacebookSdk.getCodelessSetupEnabled();
                if (z && codelessSetupEnabled) {
                    INSTANCE.checkCodelessSession(str);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void onActivityPaused(Activity activity) {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                if (isCodelessEnabled.get()) {
                    CodelessMatcher.Companion.getInstance().remove(activity);
                    ViewIndexer viewIndexer2 = viewIndexer;
                    if (viewIndexer2 != null) {
                        viewIndexer2.unschedule();
                    }
                    SensorManager sensorManager2 = sensorManager;
                    if (sensorManager2 != null) {
                        sensorManager2.unregisterListener(viewIndexingTrigger);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void onActivityDestroyed(Activity activity) {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(activity, "activity");
                CodelessMatcher.Companion.getInstance().destroy(activity);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void enable() {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isCodelessEnabled.set(true);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void disable() {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isCodelessEnabled.set(false);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void checkCodelessSession(String str) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (!isCheckingSession) {
                    isCheckingSession = true;
                    FacebookSdk.getExecutor().execute(new CodelessManager$$ExternalSyntheticLambda1(str));
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void checkCodelessSession$lambda$1(String str) {
        String str2 = "0";
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Bundle bundle = new Bundle();
                AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.Companion.getAttributionIdentifiers(FacebookSdk.getApplicationContext());
                JSONArray jSONArray = new JSONArray();
                String str3 = Build.MODEL;
                if (str3 == null) {
                    str3 = "";
                }
                jSONArray.put(str3);
                if ((attributionIdentifiers != null ? attributionIdentifiers.getAndroidAdvertiserId() : null) != null) {
                    jSONArray.put(attributionIdentifiers.getAndroidAdvertiserId());
                } else {
                    jSONArray.put("");
                }
                jSONArray.put(str2);
                if (AppEventUtility.isEmulator()) {
                    str2 = "1";
                }
                jSONArray.put(str2);
                Locale currentLocale = Utility.getCurrentLocale();
                jSONArray.put(currentLocale.getLanguage() + '_' + currentLocale.getCountry());
                String jSONArray2 = jSONArray.toString();
                Intrinsics.checkNotNullExpressionValue(jSONArray2, "extInfoArray.toString()");
                bundle.putString("device_session_id", getCurrentDeviceSessionID$facebook_core_release());
                bundle.putString("extinfo", jSONArray2);
                GraphRequest.Companion companion = GraphRequest.Companion;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                boolean z = true;
                String format = String.format(Locale.US, "%s/app_indexing_session", Arrays.copyOf(new Object[]{str}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
                JSONObject jSONObject = companion.newPostRequestWithBundle((AccessToken) null, format, bundle, (GraphRequest.Callback) null).executeAndWait().getJSONObject();
                AtomicBoolean atomicBoolean = isAppIndexingEnabled;
                if (jSONObject == null || !jSONObject.optBoolean("is_app_indexing_enabled", false)) {
                    z = false;
                }
                atomicBoolean.set(z);
                if (!atomicBoolean.get()) {
                    deviceSessionID = null;
                } else {
                    ViewIndexer viewIndexer2 = viewIndexer;
                    if (viewIndexer2 != null) {
                        viewIndexer2.schedule();
                    }
                }
                isCheckingSession = false;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final String getCurrentDeviceSessionID$facebook_core_release() {
        Class<CodelessManager> cls = CodelessManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            if (deviceSessionID == null) {
                deviceSessionID = UUID.randomUUID().toString();
            }
            String str = deviceSessionID;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            return str;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final boolean getIsAppIndexingEnabled$facebook_core_release() {
        Class<CodelessManager> cls = CodelessManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return isAppIndexingEnabled.get();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final void updateAppIndexing$facebook_core_release(boolean z) {
        Class<CodelessManager> cls = CodelessManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isAppIndexingEnabled.set(z);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
