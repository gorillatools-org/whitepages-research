package com.facebook.appevents.cloudbridge;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoggingBehavior;
import com.facebook.internal.Logger;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.messaging.Constants;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ExceptionsKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AppEventsCAPIManager {
    public static final AppEventsCAPIManager INSTANCE = new AppEventsCAPIManager();
    private static final String TAG = AppEventsCAPIManager.class.getCanonicalName();
    private static boolean isEnabled;

    private AppEventsCAPIManager() {
    }

    public final boolean isEnabled$facebook_core_release() {
        return isEnabled;
    }

    public static final Map getSavedCloudBridgeCredentials$facebook_core_release() {
        Class<AppEventsCAPIManager> cls = AppEventsCAPIManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.CloudBridgeSavedCredentials", 0);
            if (sharedPreferences == null) {
                return null;
            }
            SettingsAPIFields settingsAPIFields = SettingsAPIFields.DATASETID;
            String string = sharedPreferences.getString(settingsAPIFields.getRawValue(), (String) null);
            SettingsAPIFields settingsAPIFields2 = SettingsAPIFields.URL;
            String string2 = sharedPreferences.getString(settingsAPIFields2.getRawValue(), (String) null);
            SettingsAPIFields settingsAPIFields3 = SettingsAPIFields.ACCESSKEY;
            String string3 = sharedPreferences.getString(settingsAPIFields3.getRawValue(), (String) null);
            if (string != null) {
                if (!StringsKt.isBlank(string)) {
                    if (string2 != null) {
                        if (!StringsKt.isBlank(string2)) {
                            if (string3 != null) {
                                if (!StringsKt.isBlank(string3)) {
                                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                                    linkedHashMap.put(settingsAPIFields2.getRawValue(), string2);
                                    linkedHashMap.put(settingsAPIFields.getRawValue(), string);
                                    linkedHashMap.put(settingsAPIFields3.getRawValue(), string3);
                                    Logger.Companion.log(LoggingBehavior.APP_EVENTS, TAG.toString(), " \n\nLoading Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n ", string, string2, string3);
                                    return linkedHashMap;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public final void setSavedCloudBridgeCredentials$facebook_core_release(Map map) {
        SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.sdk.CloudBridgeSavedCredentials", 0);
        if (sharedPreferences != null) {
            if (map == null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.clear();
                edit.apply();
                return;
            }
            SettingsAPIFields settingsAPIFields = SettingsAPIFields.DATASETID;
            Object obj = map.get(settingsAPIFields.getRawValue());
            SettingsAPIFields settingsAPIFields2 = SettingsAPIFields.URL;
            Object obj2 = map.get(settingsAPIFields2.getRawValue());
            SettingsAPIFields settingsAPIFields3 = SettingsAPIFields.ACCESSKEY;
            Object obj3 = map.get(settingsAPIFields3.getRawValue());
            if (obj != null && obj2 != null && obj3 != null) {
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                edit2.putString(settingsAPIFields.getRawValue(), obj.toString());
                edit2.putString(settingsAPIFields2.getRawValue(), obj2.toString());
                edit2.putString(settingsAPIFields3.getRawValue(), obj3.toString());
                edit2.apply();
                Logger.Companion.log(LoggingBehavior.APP_EVENTS, TAG.toString(), " \n\nSaving Cloudbridge settings from saved Prefs: \n================\n DATASETID: %s\n URL: %s \n ACCESSKEY: %s \n\n ", obj, obj2, obj3);
            }
        }
    }

    public static final void enable() {
        try {
            AppEventsCAPIManager$$ExternalSyntheticLambda0 appEventsCAPIManager$$ExternalSyntheticLambda0 = new AppEventsCAPIManager$$ExternalSyntheticLambda0();
            GraphRequest graphRequest = new GraphRequest((AccessToken) null, FacebookSdk.getApplicationId() + "/cloudbridge_settings", (Bundle) null, HttpMethod.GET, appEventsCAPIManager$$ExternalSyntheticLambda0, (String) null, 32, (DefaultConstructorMarker) null);
            Logger.Companion companion = Logger.Companion;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            companion.log(loggingBehavior, str, " \n\nCreating Graph Request: \n=============\n%s\n\n ", graphRequest);
            graphRequest.executeAsync();
        } catch (JSONException e) {
            Logger.Companion companion2 = Logger.Companion;
            LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
            String str2 = TAG;
            Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
            companion2.log(loggingBehavior2, str2, " \n\nGraph Request Exception: \n=============\n%s\n\n ", ExceptionsKt.stackTraceToString(e));
        }
    }

    /* access modifiers changed from: private */
    public static final void enable$lambda$0(GraphResponse graphResponse) {
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        INSTANCE.getCAPIGSettingsFromGraphResponse$facebook_core_release(graphResponse);
    }

    public final void getCAPIGSettingsFromGraphResponse$facebook_core_release(GraphResponse graphResponse) {
        Object obj;
        boolean z;
        Intrinsics.checkNotNullParameter(graphResponse, "response");
        if (graphResponse.getError() != null) {
            Logger.Companion companion = Logger.Companion;
            LoggingBehavior loggingBehavior = LoggingBehavior.APP_EVENTS;
            String str = TAG;
            Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
            companion.log(loggingBehavior, str, " \n\nGraph Response Error: \n================\nResponse Error: %s\nResponse Error Exception: %s\n\n ", graphResponse.getError().toString(), String.valueOf(graphResponse.getError().getException()));
            Map savedCloudBridgeCredentials$facebook_core_release = getSavedCloudBridgeCredentials$facebook_core_release();
            if (savedCloudBridgeCredentials$facebook_core_release != null) {
                URL url = new URL(String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.URL.getRawValue())));
                String valueOf = String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.DATASETID.getRawValue()));
                AppEventsConversionsAPITransformerWebRequests.configure(valueOf, url.getProtocol() + "://" + url.getHost(), String.valueOf(savedCloudBridgeCredentials$facebook_core_release.get(SettingsAPIFields.ACCESSKEY.getRawValue())));
                isEnabled = true;
                return;
            }
            return;
        }
        Logger.Companion companion2 = Logger.Companion;
        LoggingBehavior loggingBehavior2 = LoggingBehavior.APP_EVENTS;
        String str2 = TAG;
        Intrinsics.checkNotNull(str2, "null cannot be cast to non-null type kotlin.String");
        companion2.log(loggingBehavior2, str2, " \n\nGraph Response Received: \n================\n%s\n\n ", graphResponse);
        JSONObject jSONObject = graphResponse.getJSONObject();
        if (jSONObject != null) {
            try {
                obj = jSONObject.get(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
            } catch (JSONException e) {
                Logger.Companion companion3 = Logger.Companion;
                LoggingBehavior loggingBehavior3 = LoggingBehavior.APP_EVENTS;
                String str3 = TAG;
                Intrinsics.checkNotNullExpressionValue(str3, "TAG");
                companion3.log(loggingBehavior3, str3, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e));
                return;
            } catch (NullPointerException e2) {
                Logger.Companion companion4 = Logger.Companion;
                LoggingBehavior loggingBehavior4 = LoggingBehavior.APP_EVENTS;
                String str4 = TAG;
                Intrinsics.checkNotNullExpressionValue(str4, "TAG");
                companion4.log(loggingBehavior4, str4, "CloudBridge Settings API response is not a valid json: \n%s ", ExceptionsKt.stackTraceToString(e2));
                return;
            }
        } else {
            obj = null;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.json.JSONArray");
        Map convertJSONObjectToHashMap = Utility.convertJSONObjectToHashMap(new JSONObject((String) CollectionsKt.firstOrNull(Utility.convertJSONArrayToList((JSONArray) obj))));
        String str5 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.URL.getRawValue());
        String str6 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.DATASETID.getRawValue());
        String str7 = (String) convertJSONObjectToHashMap.get(SettingsAPIFields.ACCESSKEY.getRawValue());
        if (str5 == null || str6 == null || str7 == null) {
            Intrinsics.checkNotNullExpressionValue(str2, "TAG");
            companion2.log(loggingBehavior2, str2, "CloudBridge Settings API response doesn't have valid data");
            return;
        }
        try {
            AppEventsConversionsAPITransformerWebRequests.configure(str6, str5, str7);
            setSavedCloudBridgeCredentials$facebook_core_release(convertJSONObjectToHashMap);
            SettingsAPIFields settingsAPIFields = SettingsAPIFields.ENABLED;
            if (convertJSONObjectToHashMap.get(settingsAPIFields.getRawValue()) != null) {
                Object obj2 = convertJSONObjectToHashMap.get(settingsAPIFields.getRawValue());
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Boolean");
                z = ((Boolean) obj2).booleanValue();
            } else {
                z = false;
            }
            isEnabled = z;
        } catch (MalformedURLException e3) {
            Logger.Companion companion5 = Logger.Companion;
            LoggingBehavior loggingBehavior5 = LoggingBehavior.APP_EVENTS;
            String str8 = TAG;
            Intrinsics.checkNotNullExpressionValue(str8, "TAG");
            companion5.log(loggingBehavior5, str8, "CloudBridge Settings API response doesn't have valid url\n %s ", ExceptionsKt.stackTraceToString(e3));
        }
    }
}
