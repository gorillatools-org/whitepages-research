package com.facebook.appevents.integrity;

import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class RedactedEventsManager {
    public static final RedactedEventsManager INSTANCE = new RedactedEventsManager();
    private static boolean enabled;
    private static Map redactedEvents = new HashMap();

    private RedactedEventsManager() {
    }

    public static final void enable() {
        Class<RedactedEventsManager> cls = RedactedEventsManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                INSTANCE.loadRedactedEvents();
                if (!redactedEvents.isEmpty()) {
                    enabled = true;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadRedactedEvents() {
        HashSet convertJSONArrayToHashSet;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    try {
                        redactedEvents = new HashMap();
                        JSONArray redactedEvents2 = queryAppSettings.getRedactedEvents();
                        if (redactedEvents2 != null && redactedEvents2.length() != 0) {
                            int length = redactedEvents2.length();
                            for (int i = 0; i < length; i++) {
                                JSONObject jSONObject = redactedEvents2.getJSONObject(i);
                                boolean has = jSONObject.has("key");
                                boolean has2 = jSONObject.has("value");
                                if (has && has2) {
                                    String string = jSONObject.getString("key");
                                    JSONArray jSONArray = jSONObject.getJSONArray("value");
                                    if (!(string == null || (convertJSONArrayToHashSet = Utility.convertJSONArrayToHashSet(jSONArray)) == null)) {
                                        redactedEvents.put(string, convertJSONArrayToHashSet);
                                    }
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r0 = INSTANCE.getRedactionString(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String processEventsRedaction(java.lang.String r3) {
        /*
            java.lang.Class<com.facebook.appevents.integrity.RedactedEventsManager> r0 = com.facebook.appevents.integrity.RedactedEventsManager.class
            boolean r1 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r0)
            r2 = 0
            if (r1 == 0) goto L_0x000a
            return r2
        L_0x000a:
            java.lang.String r1 = "eventName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)     // Catch:{ all -> 0x001c }
            boolean r1 = enabled     // Catch:{ all -> 0x001c }
            if (r1 == 0) goto L_0x001e
            com.facebook.appevents.integrity.RedactedEventsManager r1 = INSTANCE     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r1.getRedactionString(r3)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001e
            return r0
        L_0x001c:
            r3 = move-exception
            goto L_0x001f
        L_0x001e:
            return r3
        L_0x001f:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.RedactedEventsManager.processEventsRedaction(java.lang.String):java.lang.String");
    }

    private final String getRedactionString(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            for (String str2 : redactedEvents.keySet()) {
                HashSet hashSet = (HashSet) redactedEvents.get(str2);
                if (hashSet != null && hashSet.contains(str)) {
                    return str2;
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
