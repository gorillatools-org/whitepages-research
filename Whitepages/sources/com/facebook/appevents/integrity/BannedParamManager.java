package com.facebook.appevents.integrity;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.HashSet;

public final class BannedParamManager {
    public static final BannedParamManager INSTANCE = new BannedParamManager();
    private static HashSet bannedParamsConfig = new HashSet();
    private static boolean enabled;

    private BannedParamManager() {
    }

    public static final void enable() {
        Class<BannedParamManager> cls = BannedParamManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!enabled) {
                    INSTANCE.loadConfigs();
                    enabled = !bannedParamsConfig.isEmpty();
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void loadConfigs() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    bannedParamsConfig = loadSet(queryAppSettings.getBannedParams());
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:8|9|12) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return new java.util.HashSet();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.HashSet loadSet(org.json.JSONArray r3) {
        /*
            r2 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.HashSet r3 = com.facebook.internal.Utility.convertJSONArrayToHashSet(r3)     // Catch:{ Exception -> 0x0016 }
            if (r3 != 0) goto L_0x001b
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ Exception -> 0x0016 }
            r3.<init>()     // Catch:{ Exception -> 0x0016 }
            goto L_0x001b
        L_0x0014:
            r3 = move-exception
            goto L_0x001c
        L_0x0016:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ all -> 0x0014 }
            r3.<init>()     // Catch:{ all -> 0x0014 }
        L_0x001b:
            return r3
        L_0x001c:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.integrity.BannedParamManager.loadSet(org.json.JSONArray):java.util.HashSet");
    }

    public static final void processFilterBannedParams(Bundle bundle) {
        Class<BannedParamManager> cls = BannedParamManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (!enabled) {
                    return;
                }
                if (bundle != null) {
                    for (String remove : bannedParamsConfig) {
                        bundle.remove(remove);
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }
}
