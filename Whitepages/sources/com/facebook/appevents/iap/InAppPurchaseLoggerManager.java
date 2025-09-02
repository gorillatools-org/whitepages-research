package com.facebook.appevents.iap;

import android.content.SharedPreferences;
import com.facebook.FacebookSdk;
import com.facebook.appevents.iap.InAppPurchaseUtils;
import com.facebook.appevents.internal.AutomaticAnalyticsLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class InAppPurchaseLoggerManager {
    public static final InAppPurchaseLoggerManager INSTANCE = new InAppPurchaseLoggerManager();

    private InAppPurchaseLoggerManager() {
    }

    public static final void migrateOldCacheHistory() {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.IAP_CACHE_GPBLV2V7", 0);
                long max = Math.max(Math.max(sharedPreferences.getLong("TIME_OF_LAST_LOGGED_PURCHASE", 0), sharedPreferences.getLong("TIME_OF_LAST_LOGGED_SUBSCRIPTION", 0)), 1736528400000L);
                CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
                SharedPreferences sharedPreferences2 = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.PRODUCT_DETAILS", 0);
                if (sharedPreferences2.contains("PURCHASE_DETAILS_SET")) {
                    Set<String> stringSet = sharedPreferences2.getStringSet("PURCHASE_DETAILS_SET", new HashSet());
                    copyOnWriteArraySet.addAll(stringSet == null ? new HashSet() : stringSet);
                    for (String split$default : copyOnWriteArraySet) {
                        try {
                            long parseLong = Long.parseLong((String) StringsKt.split$default((CharSequence) split$default, new String[]{";"}, false, 2, 2, (Object) null).get(1)) * 1000;
                            if (((double) Math.abs(String.valueOf(parseLong).length() - 13)) < Math.log10(1000.0d)) {
                                max = Math.max(max, parseLong);
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
                sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_SUBSCRIPTION", max).apply();
                sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_PURCHASE", max).apply();
                deleteOldCacheHistory();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void deleteOldCacheHistory() {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.SKU_DETAILS", 0);
                SharedPreferences sharedPreferences2 = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.PURCHASE", 0);
                sharedPreferences.edit().clear().apply();
                sharedPreferences2.edit().clear().apply();
                FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.PRODUCT_DETAILS", 0).edit().clear().apply();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final boolean getIsFirstAppLaunchWithNewIAP() {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return !FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.IAP_CACHE_GPBLV2V7", 0).contains("APP_HAS_BEEN_LAUNCHED_KEY");
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    public static final void setAppHasBeenLaunchedWithNewIAP() {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                try {
                    FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.IAP_CACHE_GPBLV2V7", 0).edit().putBoolean("APP_HAS_BEEN_LAUNCHED_KEY", true).apply();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void updateLatestPossiblePurchaseTime() {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                setAppHasBeenLaunchedWithNewIAP();
                try {
                    SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.IAP_CACHE_GPBLV2V7", 0);
                    long currentTimeMillis = System.currentTimeMillis();
                    sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_SUBSCRIPTION", currentTimeMillis).apply();
                    sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_PURCHASE", currentTimeMillis).apply();
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final void filterPurchaseLogging(Map map, Map map2, boolean z, String str, InAppPurchaseUtils.BillingClientVersion billingClientVersion, boolean z2) {
        Class<InAppPurchaseLoggerManager> cls = InAppPurchaseLoggerManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(map, "purchaseDetailsMap");
                Intrinsics.checkNotNullParameter(map2, "skuDetailsMap");
                Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
                Intrinsics.checkNotNullParameter(billingClientVersion, "billingClientVersion");
                InAppPurchaseLoggerManager inAppPurchaseLoggerManager = INSTANCE;
                inAppPurchaseLoggerManager.logPurchases(inAppPurchaseLoggerManager.constructLoggingReadyMap$facebook_core_release(inAppPurchaseLoggerManager.cacheDeDupPurchase$facebook_core_release(map, z), map2, str), z, billingClientVersion, z2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    private final void logPurchases(Map map, boolean z, InAppPurchaseUtils.BillingClientVersion billingClientVersion, boolean z2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                for (Map.Entry entry : map.entrySet()) {
                    AutomaticAnalyticsLogger.logPurchase((String) entry.getKey(), (String) entry.getValue(), z, billingClientVersion, z2);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    public final Map cacheDeDupPurchase$facebook_core_release(Map map, boolean z) {
        long j;
        Map map2 = map;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(map2, "purchaseDetailsMap");
            SharedPreferences sharedPreferences = FacebookSdk.getApplicationContext().getSharedPreferences("com.facebook.internal.iap.IAP_CACHE_GPBLV2V7", 0);
            if (z) {
                j = sharedPreferences.getLong("TIME_OF_LAST_LOGGED_SUBSCRIPTION", 1736528400000L);
            } else {
                j = sharedPreferences.getLong("TIME_OF_LAST_LOGGED_PURCHASE", 1736528400000L);
            }
            long j2 = 0;
            for (Map.Entry entry : MapsKt.toMap(map).entrySet()) {
                String str = (String) entry.getKey();
                JSONObject jSONObject = (JSONObject) entry.getValue();
                try {
                    if (jSONObject.has("purchaseToken") && jSONObject.has("purchaseTime")) {
                        long j3 = jSONObject.getLong("purchaseTime");
                        if (j3 <= j) {
                            map2.remove(str);
                        }
                        j2 = Math.max(j2, j3);
                    }
                } catch (Exception unused) {
                }
            }
            if (j2 >= j) {
                if (z) {
                    sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_SUBSCRIPTION", j2).apply();
                } else {
                    sharedPreferences.edit().putLong("TIME_OF_LAST_LOGGED_PURCHASE", j2).apply();
                }
            }
            return new HashMap(map2);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public final Map constructLoggingReadyMap$facebook_core_release(Map map, Map map2, String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(map, "purchaseDetailsMap");
            Intrinsics.checkNotNullParameter(map2, "skuDetailsMap");
            Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : map.entrySet()) {
                JSONObject jSONObject = (JSONObject) entry.getValue();
                JSONObject jSONObject2 = (JSONObject) map2.get((String) entry.getKey());
                try {
                    jSONObject.put(RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME, str);
                    if (jSONObject2 != null) {
                        String jSONObject3 = jSONObject.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject3, "purchaseDetail.toString()");
                        String jSONObject4 = jSONObject2.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject4, "skuDetail.toString()");
                        linkedHashMap.put(jSONObject3, jSONObject4);
                    }
                } catch (Exception unused) {
                }
            }
            return linkedHashMap;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
