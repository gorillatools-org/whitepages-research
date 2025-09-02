package com.facebook.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.appevents.codeless.internal.UnityReflection;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.appevents.internal.Constants;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.FetchedAppSettings;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.http2.Http2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class FetchedAppSettingsManager {
    private static final List APP_SETTING_FIELDS = CollectionsKt.listOf("supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "android_dialog_configs", "android_sdk_error_categories", "app_events_session_timeout", "app_events_feature_bitmask", "auto_event_mapping_android", "seamless_login", "smart_login_bookmark_icon_url", "smart_login_menu_icon_url", "restrictive_data_filter_params", "aam_rules", "suggested_events_setting", "protected_mode_rules", "auto_log_app_events_default", "auto_log_app_events_enabled", "app_events_config.os_version(" + Build.VERSION.RELEASE + ')');
    public static final FetchedAppSettingsManager INSTANCE = new FetchedAppSettingsManager();
    private static final String TAG = FetchedAppSettingsManager.class.getSimpleName();
    private static final Map fetchedAppSettings = new ConcurrentHashMap();
    private static final ConcurrentLinkedQueue fetchedAppSettingsCallbacks = new ConcurrentLinkedQueue();
    private static final AtomicReference loadingState = new AtomicReference(FetchAppSettingState.NOT_LOADED);
    private static boolean printedSDKUpdatedMessage;
    private static JSONArray unityEventBindings;

    public enum FetchAppSettingState {
        NOT_LOADED,
        LOADING,
        SUCCESS,
        ERROR
    }

    public interface FetchedAppSettingsCallback {
        void onError();

        void onSuccess(FetchedAppSettings fetchedAppSettings);
    }

    private FetchedAppSettingsManager() {
    }

    public static final void loadAppSettingsAsync() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        if (Utility.isNullOrEmpty(applicationId)) {
            loadingState.set(FetchAppSettingState.ERROR);
            INSTANCE.pollCallbacks();
        } else if (fetchedAppSettings.containsKey(applicationId)) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            INSTANCE.pollCallbacks();
        } else {
            AtomicReference atomicReference = loadingState;
            FetchAppSettingState fetchAppSettingState = FetchAppSettingState.NOT_LOADED;
            FetchAppSettingState fetchAppSettingState2 = FetchAppSettingState.LOADING;
            if (FetchedAppSettingsManager$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, fetchAppSettingState, fetchAppSettingState2) || FetchedAppSettingsManager$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, FetchAppSettingState.ERROR, fetchAppSettingState2)) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("com.facebook.internal.APP_SETTINGS.%s", Arrays.copyOf(new Object[]{applicationId}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                FacebookSdk.getExecutor().execute(new FetchedAppSettingsManager$$ExternalSyntheticLambda1(applicationContext, format, applicationId));
                return;
            }
            INSTANCE.pollCallbacks();
        }
    }

    /* access modifiers changed from: private */
    public static final void loadAppSettingsAsync$lambda$0(Context context, String str, String str2) {
        FetchAppSettingState fetchAppSettingState;
        JSONObject jSONObject;
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(str, "$settingsKey");
        Intrinsics.checkNotNullParameter(str2, "$applicationId");
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0);
        FetchedAppSettings fetchedAppSettings2 = null;
        String string = sharedPreferences.getString(str, (String) null);
        if (!Utility.isNullOrEmpty(string)) {
            if (string != null) {
                try {
                    jSONObject = new JSONObject(string);
                } catch (JSONException e) {
                    Utility.logd("FacebookSDK", (Exception) e);
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    fetchedAppSettings2 = INSTANCE.parseAppSettingsFromJSON$facebook_core_release(str2, jSONObject);
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        FetchedAppSettingsManager fetchedAppSettingsManager = INSTANCE;
        JSONObject appSettingsQueryResponse = fetchedAppSettingsManager.getAppSettingsQueryResponse(str2);
        if (appSettingsQueryResponse != null) {
            fetchedAppSettingsManager.parseAppSettingsFromJSON$facebook_core_release(str2, appSettingsQueryResponse);
            sharedPreferences.edit().putString(str, appSettingsQueryResponse.toString()).apply();
        }
        if (fetchedAppSettings2 != null) {
            String sdkUpdateMessage = fetchedAppSettings2.getSdkUpdateMessage();
            if (!printedSDKUpdatedMessage && sdkUpdateMessage != null && sdkUpdateMessage.length() > 0) {
                printedSDKUpdatedMessage = true;
                Log.w(TAG, sdkUpdateMessage);
            }
        }
        FetchedAppGateKeepersManager.queryAppGateKeepers(str2, true);
        AutomaticAnalyticsLogger.logActivateAppEvent();
        AtomicReference atomicReference = loadingState;
        if (fetchedAppSettings.containsKey(str2)) {
            fetchAppSettingState = FetchAppSettingState.SUCCESS;
        } else {
            fetchAppSettingState = FetchAppSettingState.ERROR;
        }
        atomicReference.set(fetchAppSettingState);
        fetchedAppSettingsManager.pollCallbacks();
    }

    public static final FetchedAppSettings getAppSettingsWithoutQuery(String str) {
        if (str != null) {
            return (FetchedAppSettings) fetchedAppSettings.get(str);
        }
        return null;
    }

    public static final void getAppSettingsAsync(FetchedAppSettingsCallback fetchedAppSettingsCallback) {
        Intrinsics.checkNotNullParameter(fetchedAppSettingsCallback, "callback");
        fetchedAppSettingsCallbacks.add(fetchedAppSettingsCallback);
        loadAppSettingsAsync();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void pollCallbacks() {
        /*
            r4 = this;
            monitor-enter(r4)
            java.util.concurrent.atomic.AtomicReference r0 = loadingState     // Catch:{ all -> 0x0042 }
            java.lang.Object r0 = r0.get()     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r0 = (com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState) r0     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r1 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.NOT_LOADED     // Catch:{ all -> 0x0042 }
            if (r1 == r0) goto L_0x005f
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r1 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.LOADING     // Catch:{ all -> 0x0042 }
            if (r1 != r0) goto L_0x0012
            goto L_0x005f
        L_0x0012:
            java.lang.String r1 = com.facebook.FacebookSdk.getApplicationId()     // Catch:{ all -> 0x0042 }
            java.util.Map r2 = fetchedAppSettings     // Catch:{ all -> 0x0042 }
            java.lang.Object r1 = r2.get(r1)     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettings r1 = (com.facebook.internal.FetchedAppSettings) r1     // Catch:{ all -> 0x0042 }
            android.os.Handler r2 = new android.os.Handler     // Catch:{ all -> 0x0042 }
            android.os.Looper r3 = android.os.Looper.getMainLooper()     // Catch:{ all -> 0x0042 }
            r2.<init>(r3)     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$FetchAppSettingState r3 = com.facebook.internal.FetchedAppSettingsManager.FetchAppSettingState.ERROR     // Catch:{ all -> 0x0042 }
            if (r3 != r0) goto L_0x0046
        L_0x002b:
            java.util.concurrent.ConcurrentLinkedQueue r0 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0042 }
            boolean r1 = r0.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0044
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback r0 = (com.facebook.internal.FetchedAppSettingsManager.FetchedAppSettingsCallback) r0     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda2 r1 = new com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda2     // Catch:{ all -> 0x0042 }
            r1.<init>(r0)     // Catch:{ all -> 0x0042 }
            r2.post(r1)     // Catch:{ all -> 0x0042 }
            goto L_0x002b
        L_0x0042:
            r0 = move-exception
            goto L_0x0061
        L_0x0044:
            monitor-exit(r4)
            return
        L_0x0046:
            java.util.concurrent.ConcurrentLinkedQueue r0 = fetchedAppSettingsCallbacks     // Catch:{ all -> 0x0042 }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r3 != 0) goto L_0x005d
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$FetchedAppSettingsCallback r0 = (com.facebook.internal.FetchedAppSettingsManager.FetchedAppSettingsCallback) r0     // Catch:{ all -> 0x0042 }
            com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda3 r3 = new com.facebook.internal.FetchedAppSettingsManager$$ExternalSyntheticLambda3     // Catch:{ all -> 0x0042 }
            r3.<init>(r0, r1)     // Catch:{ all -> 0x0042 }
            r2.post(r3)     // Catch:{ all -> 0x0042 }
            goto L_0x0046
        L_0x005d:
            monitor-exit(r4)
            return
        L_0x005f:
            monitor-exit(r4)
            return
        L_0x0061:
            monitor-exit(r4)     // Catch:{ all -> 0x0042 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FetchedAppSettingsManager.pollCallbacks():void");
    }

    /* access modifiers changed from: private */
    public static final void pollCallbacks$lambda$1(FetchedAppSettingsCallback fetchedAppSettingsCallback) {
        fetchedAppSettingsCallback.onError();
    }

    /* access modifiers changed from: private */
    public static final void pollCallbacks$lambda$2(FetchedAppSettingsCallback fetchedAppSettingsCallback, FetchedAppSettings fetchedAppSettings2) {
        fetchedAppSettingsCallback.onSuccess(fetchedAppSettings2);
    }

    public static final Map getCachedMigratedAutoLogValuesInAppSettings() {
        JSONObject jSONObject;
        Context applicationContext = FacebookSdk.getApplicationContext();
        String applicationId = FacebookSdk.getApplicationId();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("com.facebook.internal.APP_SETTINGS.%s", Arrays.copyOf(new Object[]{applicationId}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        String string = applicationContext.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).getString(format, (String) null);
        if (!Utility.isNullOrEmpty(string)) {
            if (string != null) {
                try {
                    jSONObject = new JSONObject(string);
                } catch (JSONException e) {
                    Utility.logd("FacebookSDK", (Exception) e);
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    return INSTANCE.parseMigratedAutoLogValues(jSONObject);
                }
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        return null;
    }

    public static final FetchedAppSettings queryAppSettings(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "applicationId");
        if (!z) {
            Map map = fetchedAppSettings;
            if (map.containsKey(str)) {
                return (FetchedAppSettings) map.get(str);
            }
        }
        FetchedAppSettingsManager fetchedAppSettingsManager = INSTANCE;
        FetchedAppSettings parseAppSettingsFromJSON$facebook_core_release = fetchedAppSettingsManager.parseAppSettingsFromJSON$facebook_core_release(str, fetchedAppSettingsManager.getAppSettingsQueryResponse(str));
        if (Intrinsics.areEqual((Object) str, (Object) FacebookSdk.getApplicationId())) {
            loadingState.set(FetchAppSettingState.SUCCESS);
            fetchedAppSettingsManager.pollCallbacks();
        }
        return parseAppSettingsFromJSON$facebook_core_release;
    }

    public final FetchedAppSettings parseAppSettingsFromJSON$facebook_core_release(String str, JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        Intrinsics.checkNotNullParameter(str, "applicationId");
        Intrinsics.checkNotNullParameter(jSONObject2, "settingsJSON");
        JSONArray optJSONArray = jSONObject2.optJSONArray("android_sdk_error_categories");
        FacebookRequestErrorClassification.Companion companion = FacebookRequestErrorClassification.Companion;
        FacebookRequestErrorClassification createFromJSON = companion.createFromJSON(optJSONArray);
        if (createFromJSON == null) {
            createFromJSON = companion.getDefaultErrorClassification();
        }
        FacebookRequestErrorClassification facebookRequestErrorClassification = createFromJSON;
        int optInt = jSONObject2.optInt("app_events_feature_bitmask", 0);
        boolean z = (optInt & 8) != 0;
        boolean z2 = (optInt & 16) != 0;
        boolean z3 = (optInt & 32) != 0;
        boolean z4 = (optInt & 256) != 0;
        boolean z5 = (optInt & Http2.INITIAL_MAX_FRAME_SIZE) != 0;
        JSONArray optJSONArray2 = jSONObject2.optJSONArray("auto_event_mapping_android");
        unityEventBindings = optJSONArray2;
        if (optJSONArray2 != null && InternalSettings.isUnityApp()) {
            UnityReflection.sendEventMapping(optJSONArray2 != null ? optJSONArray2.toString() : null);
        }
        JSONObject optJSONObject = jSONObject2.optJSONObject("app_events_config");
        boolean optBoolean = jSONObject2.optBoolean("supports_implicit_sdk_logging", false);
        String optString = jSONObject2.optString("gdpv4_nux_content", "");
        String str2 = optString;
        Intrinsics.checkNotNullExpressionValue(optString, "settingsJSON.optString(A…_SETTING_NUX_CONTENT, \"\")");
        boolean optBoolean2 = jSONObject2.optBoolean("gdpv4_nux_enabled", false);
        int optInt2 = jSONObject2.optInt("app_events_session_timeout", Constants.getDefaultAppEventsSessionTimeoutInSeconds());
        FacebookRequestErrorClassification facebookRequestErrorClassification2 = facebookRequestErrorClassification;
        EnumSet parseOptions = SmartLoginOption.Companion.parseOptions(jSONObject2.optLong("seamless_login"));
        JSONObject jSONObject3 = optJSONObject;
        Map parseDialogConfigurations = parseDialogConfigurations(jSONObject2.optJSONObject("android_dialog_configs"));
        String optString2 = jSONObject2.optString("smart_login_bookmark_icon_url");
        String str3 = optString2;
        Intrinsics.checkNotNullExpressionValue(optString2, "settingsJSON.optString(S…_LOGIN_BOOKMARK_ICON_URL)");
        String optString3 = jSONObject2.optString("smart_login_menu_icon_url");
        String str4 = optString3;
        Intrinsics.checkNotNullExpressionValue(optString3, "settingsJSON.optString(SMART_LOGIN_MENU_ICON_URL)");
        String optString4 = jSONObject2.optString("sdk_update_message");
        Intrinsics.checkNotNullExpressionValue(optString4, "settingsJSON.optString(SDK_UPDATE_MESSAGE)");
        FetchedAppSettings fetchedAppSettings2 = r4;
        FetchedAppSettings fetchedAppSettings3 = new FetchedAppSettings(optBoolean, str2, optBoolean2, optInt2, parseOptions, parseDialogConfigurations, z, facebookRequestErrorClassification2, str3, str4, z2, z3, optJSONArray2, optString4, z4, z5, jSONObject2.optString("aam_rules"), jSONObject2.optString("suggested_events_setting"), jSONObject2.optString("restrictive_data_filter_params"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "standard_params"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "maca_rules"), parseMigratedAutoLogValues(jSONObject2), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "blocklist_events"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "redacted_events"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "sensitive_params"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "standard_params_schema"), parseProtectedModeRules(jSONObject2.optJSONObject("protected_mode_rules"), "standard_params_blocked"), parseCurrencyAndValueDedupeParameters(jSONObject3, "fb_currency"), parseCurrencyAndValueDedupeParameters(jSONObject3, "_valueToSum"), parseDedupeParameters$default(this, jSONObject3, false, 2, (Object) null), parseDedupeParameters(jSONObject3, true), parseDedupeWindow(jSONObject2.optJSONObject("app_events_config")));
        fetchedAppSettings.put(str, fetchedAppSettings2);
        return fetchedAppSettings2;
    }

    private final JSONObject getAppSettingsQueryResponse(String str) {
        Bundle bundle = new Bundle();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(APP_SETTING_FIELDS);
        bundle.putString("fields", TextUtils.join(",", arrayList));
        GraphRequest newGraphPathRequest = GraphRequest.Companion.newGraphPathRequest((AccessToken) null, "app", (GraphRequest.Callback) null);
        newGraphPathRequest.setForceApplicationRequest(true);
        newGraphPathRequest.setParameters(bundle);
        JSONObject jsonObject = newGraphPathRequest.executeAndWait().getJsonObject();
        return jsonObject == null ? new JSONObject() : jsonObject;
    }

    private final Map parseDialogConfigurations(JSONObject jSONObject) {
        JSONArray optJSONArray;
        HashMap hashMap = new HashMap();
        if (!(jSONObject == null || (optJSONArray = jSONObject.optJSONArray(Constants.ScionAnalytics.MessageType.DATA_MESSAGE)) == null)) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                FetchedAppSettings.DialogFeatureConfig.Companion companion = FetchedAppSettings.DialogFeatureConfig.Companion;
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                Intrinsics.checkNotNullExpressionValue(optJSONObject, "dialogConfigData.optJSONObject(i)");
                FetchedAppSettings.DialogFeatureConfig parseDialogConfig = companion.parseDialogConfig(optJSONObject);
                if (parseDialogConfig != null) {
                    String dialogName = parseDialogConfig.getDialogName();
                    Map map = (Map) hashMap.get(dialogName);
                    if (map == null) {
                        map = new HashMap();
                        hashMap.put(dialogName, map);
                    }
                    map.put(parseDialogConfig.getFeatureName(), parseDialogConfig);
                }
            }
        }
        return hashMap;
    }

    private final Long parseDedupeWindow(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            return Long.valueOf(jSONObject.optLong("iap_manual_and_auto_log_dedup_window_millis"));
        } catch (Exception unused) {
            return null;
        }
    }

    private final JSONArray parseProtectedModeRules(JSONObject jSONObject, String str) {
        if (jSONObject != null) {
            return jSONObject.optJSONArray(str);
        }
        return null;
    }

    private final List parseCurrencyAndValueDedupeParameters(JSONObject jSONObject, String str) {
        JSONArray jSONArray;
        if (jSONObject != null) {
            try {
                jSONArray = jSONObject.getJSONArray("iap_manual_and_auto_log_dedup_keys");
            } catch (Exception unused) {
            }
        } else {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (Intrinsics.areEqual((Object) jSONObject2.getString("key"), (Object) "prod_keys")) {
                JSONArray jSONArray2 = jSONObject2.getJSONArray("value");
                int length2 = jSONArray2.length();
                int i2 = 0;
                while (i2 < length2) {
                    JSONObject jSONObject3 = jSONArray2.getJSONObject(i2);
                    if (!Intrinsics.areEqual((Object) jSONObject3.getString("key"), (Object) str)) {
                        i2++;
                    } else {
                        JSONArray jSONArray3 = jSONObject3.getJSONArray("value");
                        ArrayList arrayList = new ArrayList();
                        int length3 = jSONArray3.length();
                        for (int i3 = 0; i3 < length3; i3++) {
                            arrayList.add(jSONArray3.getJSONObject(i3).getString("value"));
                        }
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.addAll(arrayList);
                        return arrayList2;
                    }
                }
                continue;
            }
        }
        return null;
    }

    static /* synthetic */ ArrayList parseDedupeParameters$default(FetchedAppSettingsManager fetchedAppSettingsManager, JSONObject jSONObject, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return fetchedAppSettingsManager.parseDedupeParameters(jSONObject, z);
    }

    private final ArrayList parseDedupeParameters(JSONObject jSONObject, boolean z) {
        JSONArray jSONArray;
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 != null) {
            try {
                jSONArray = jSONObject2.getJSONArray("iap_manual_and_auto_log_dedup_keys");
            } catch (Exception unused) {
                return null;
            }
        } else {
            jSONArray = null;
        }
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        ArrayList arrayList = null;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            String string = jSONObject3.getString("key");
            if (!Intrinsics.areEqual((Object) string, (Object) "prod_keys") || !z) {
                if (!Intrinsics.areEqual((Object) string, (Object) "test_keys") || z) {
                    JSONArray jSONArray2 = jSONObject3.getJSONArray("value");
                    int length2 = jSONArray2.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        JSONObject jSONObject4 = jSONArray2.getJSONObject(i2);
                        String string2 = jSONObject4.getString("key");
                        if (!Intrinsics.areEqual((Object) string2, (Object) "_valueToSum")) {
                            if (!Intrinsics.areEqual((Object) string2, (Object) "fb_currency")) {
                                JSONArray jSONArray3 = jSONObject4.getJSONArray("value");
                                ArrayList arrayList2 = new ArrayList();
                                int length3 = jSONArray3.length();
                                for (int i3 = 0; i3 < length3; i3++) {
                                    arrayList2.add(jSONArray3.getJSONObject(i3).getString("value"));
                                }
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(new Pair(string2, arrayList2));
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private final Map parseMigratedAutoLogValues(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        if (!jSONObject.isNull("auto_log_app_events_default")) {
            try {
                hashMap.put("auto_log_app_events_default", Boolean.valueOf(jSONObject.getBoolean("auto_log_app_events_default")));
            } catch (JSONException e) {
                Utility.logd("FacebookSDK", (Exception) e);
            }
        }
        if (!jSONObject.isNull("auto_log_app_events_enabled")) {
            try {
                hashMap.put("auto_log_app_events_enabled", Boolean.valueOf(jSONObject.getBoolean("auto_log_app_events_enabled")));
            } catch (JSONException e2) {
                Utility.logd("FacebookSDK", (Exception) e2);
            }
        }
        if (hashMap.isEmpty()) {
            return null;
        }
        return hashMap;
    }
}
