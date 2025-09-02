package com.facebook.appevents.restrictivedatafilter;

import android.util.Log;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.salesforce.marketingcloud.config.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

public final class RestrictiveDataManager {
    public static final RestrictiveDataManager INSTANCE = new RestrictiveDataManager();
    private static final String TAG = RestrictiveDataManager.class.getCanonicalName();
    private static boolean enabled;
    private static final Set restrictedEvents = new CopyOnWriteArraySet();
    private static final List restrictiveParamFilters = new ArrayList();

    private RestrictiveDataManager() {
    }

    public static final void enable() {
        Class<RestrictiveDataManager> cls = RestrictiveDataManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                enabled = true;
                INSTANCE.initialize();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void initialize() {
        String restrictiveDataSetting;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null && (restrictiveDataSetting = queryAppSettings.getRestrictiveDataSetting()) != null) {
                    if (restrictiveDataSetting.length() != 0) {
                        JSONObject jSONObject = new JSONObject(restrictiveDataSetting);
                        restrictiveParamFilters.clear();
                        restrictedEvents.clear();
                        Iterator<String> keys = jSONObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                            if (jSONObject2 != null) {
                                JSONObject optJSONObject = jSONObject2.optJSONObject("restrictive_param");
                                Intrinsics.checkNotNullExpressionValue(next, "key");
                                RestrictiveParamFilter restrictiveParamFilter = new RestrictiveParamFilter(next, new HashMap());
                                if (optJSONObject != null) {
                                    restrictiveParamFilter.setRestrictiveParams(Utility.convertJSONObjectToStringMap(optJSONObject));
                                    restrictiveParamFilters.add(restrictiveParamFilter);
                                }
                                if (jSONObject2.has("process_event_name")) {
                                    restrictedEvents.add(restrictiveParamFilter.getEventName());
                                }
                            }
                        }
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public static final String processEvent(String str) {
        Class<RestrictiveDataManager> cls = RestrictiveDataManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, a.h);
            return (!enabled || !INSTANCE.isRestrictedEvent(str)) ? str : "_removed_";
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void processParameters(Map map, String str) {
        Class<RestrictiveDataManager> cls = RestrictiveDataManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(map, "parameters");
                Intrinsics.checkNotNullParameter(str, a.h);
                if (enabled) {
                    HashMap hashMap = new HashMap();
                    for (String str2 : new ArrayList(map.keySet())) {
                        String matchedRuleType = INSTANCE.getMatchedRuleType(str, str2);
                        if (matchedRuleType != null) {
                            hashMap.put(str2, matchedRuleType);
                            map.remove(str2);
                        }
                    }
                    if (!hashMap.isEmpty()) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            for (Map.Entry entry : hashMap.entrySet()) {
                                jSONObject.put((String) entry.getKey(), (String) entry.getValue());
                            }
                            map.put("_restrictedParams", jSONObject.toString());
                        } catch (JSONException unused) {
                        }
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final String getMatchedRuleType(String str, String str2) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            for (RestrictiveParamFilter restrictiveParamFilter : new ArrayList(restrictiveParamFilters)) {
                if (restrictiveParamFilter != null) {
                    if (Intrinsics.areEqual((Object) str, (Object) restrictiveParamFilter.getEventName())) {
                        for (String str3 : restrictiveParamFilter.getRestrictiveParams().keySet()) {
                            if (Intrinsics.areEqual((Object) str2, (Object) str3)) {
                                return (String) restrictiveParamFilter.getRestrictiveParams().get(str3);
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TAG, "getMatchedRuleType failed", e);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
        return null;
    }

    private final boolean isRestrictedEvent(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            return restrictedEvents.contains(str);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    public static final class RestrictiveParamFilter {
        private String eventName;
        private Map restrictiveParams;

        public RestrictiveParamFilter(String str, Map map) {
            Intrinsics.checkNotNullParameter(str, a.h);
            Intrinsics.checkNotNullParameter(map, "restrictiveParams");
            this.eventName = str;
            this.restrictiveParams = map;
        }

        public final String getEventName() {
            return this.eventName;
        }

        public final Map getRestrictiveParams() {
            return this.restrictiveParams;
        }

        public final void setRestrictiveParams(Map map) {
            Intrinsics.checkNotNullParameter(map, "<set-?>");
            this.restrictiveParams = map;
        }
    }
}
