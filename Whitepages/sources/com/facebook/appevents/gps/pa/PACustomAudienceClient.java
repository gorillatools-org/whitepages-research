package com.facebook.appevents.gps.pa;

import android.adservices.common.AdData;
import android.adservices.common.AdSelectionSignals;
import android.adservices.common.AdTechIdentifier;
import android.adservices.customaudience.CustomAudience;
import android.adservices.customaudience.CustomAudienceManager;
import android.adservices.customaudience.JoinCustomAudienceRequest;
import android.adservices.customaudience.TrustedBiddingData;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.core.os.OutcomeReceiverKt$$ExternalSyntheticApiModelOutline0;
import com.facebook.FacebookSdk;
import com.facebook.appevents.gps.GpsDebugLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.concurrent.Executors;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class PACustomAudienceClient {
    public static final PACustomAudienceClient INSTANCE = new PACustomAudienceClient();
    private static final String TAG = ("Fledge: " + PACustomAudienceClient.class.getSimpleName());
    private static String baseUri;
    private static CustomAudienceManager customAudienceManager;
    private static boolean enabled;
    private static GpsDebugLogger gpsDebugLogger;
    private static boolean isInitialized;

    private PACustomAudienceClient() {
    }

    public static final /* synthetic */ GpsDebugLogger access$getGpsDebugLogger$p() {
        Class<PACustomAudienceClient> cls = PACustomAudienceClient.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return gpsDebugLogger;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final /* synthetic */ String access$getTAG$p() {
        Class<PACustomAudienceClient> cls = PACustomAudienceClient.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return TAG;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final void enable() {
        GpsDebugLogger gpsDebugLogger2;
        String str;
        Class<PACustomAudienceClient> cls = PACustomAudienceClient.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                isInitialized = true;
                Context applicationContext = FacebookSdk.getApplicationContext();
                gpsDebugLogger = new GpsDebugLogger(applicationContext);
                baseUri = "https://www." + FacebookSdk.getFacebookDomain() + "/privacy_sandbox/pa/logic";
                gpsDebugLogger2 = null;
                CustomAudienceManager m = CustomAudienceManager.get(applicationContext);
                customAudienceManager = m;
                if (m != null) {
                    enabled = true;
                }
                str = null;
            } catch (Exception e) {
                str = e.toString();
                Log.w(TAG, "Failed to get CustomAudienceManager: " + e);
            } catch (Error e2) {
                str = e2.toString();
                Log.w(TAG, "Failed to get CustomAudienceManager: " + e2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
                return;
            }
            if (!enabled) {
                GpsDebugLogger gpsDebugLogger3 = gpsDebugLogger;
                if (gpsDebugLogger3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                } else {
                    gpsDebugLogger2 = gpsDebugLogger3;
                }
                Bundle bundle = new Bundle();
                bundle.putString("gps_pa_failed_reason", str);
                Unit unit = Unit.INSTANCE;
                gpsDebugLogger2.log("gps_pa_failed", bundle);
            }
        }
    }

    public final void joinCustomAudience(String str, String str2) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                if (!isInitialized) {
                    enable();
                }
                if (enabled) {
                    joinCustomAudienceImpl(str, str2);
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        android.util.Log.w(TAG, "Failed to get event name from event.");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:18:0x0026 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void joinCustomAudience(java.lang.String r3, com.facebook.appevents.AppEvent r4) {
        /*
            r2 = this;
            boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r2)
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = isInitialized     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0011
            enable()     // Catch:{ all -> 0x000f }
            goto L_0x0011
        L_0x000f:
            r3 = move-exception
            goto L_0x0031
        L_0x0011:
            boolean r0 = enabled     // Catch:{ all -> 0x000f }
            if (r0 != 0) goto L_0x0016
            return
        L_0x0016:
            r0 = 0
            if (r4 == 0) goto L_0x002d
            org.json.JSONObject r4 = r4.getJSONObject()     // Catch:{ JSONException -> 0x0026 }
            if (r4 == 0) goto L_0x002d
            java.lang.String r1 = "_eventName"
            java.lang.String r0 = r4.getString(r1)     // Catch:{ JSONException -> 0x0026 }
            goto L_0x002d
        L_0x0026:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x000f }
            java.lang.String r1 = "Failed to get event name from event."
            android.util.Log.w(r4, r1)     // Catch:{ all -> 0x000f }
        L_0x002d:
            r2.joinCustomAudienceImpl(r3, r0)     // Catch:{ all -> 0x000f }
            return
        L_0x0031:
            com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.appevents.gps.pa.PACustomAudienceClient.joinCustomAudience(java.lang.String, com.facebook.appevents.AppEvent):void");
    }

    private final void joinCustomAudienceImpl(String str, String str2) {
        GpsDebugLogger gpsDebugLogger2;
        GpsDebugLogger gpsDebugLogger3;
        GpsDebugLogger gpsDebugLogger4;
        boolean isObjectCrashing = CrashShieldHandler.isObjectCrashing(this);
        if (!isObjectCrashing) {
            try {
                gpsDebugLogger2 = isObjectCrashing;
                gpsDebugLogger2 = isObjectCrashing;
                String validateAndCreateCAName = validateAndCreateCAName(str, str2);
                if (validateAndCreateCAName != null) {
                    gpsDebugLogger2 = false;
                    OutcomeReceiver m = OutcomeReceiverKt$$ExternalSyntheticApiModelOutline0.m(new PACustomAudienceClient$joinCustomAudienceImpl$callback$1());
                    PACustomAudienceClient$$ExternalSyntheticApiModelOutline24.m();
                    AdData.Builder m2 = PACustomAudienceClient$$ExternalSyntheticApiModelOutline20.m();
                    StringBuilder sb = new StringBuilder();
                    String str3 = baseUri;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str3 = null;
                    }
                    sb.append(str3);
                    sb.append("/ad");
                    Uri parse = Uri.parse(sb.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
                    AdData m3 = m2.setRenderUri(parse).setMetadata("{'isRealAd': false}").build();
                    Intrinsics.checkNotNullExpressionValue(m3, "Builder()\n              …\n                .build()");
                    PACustomAudienceClient$$ExternalSyntheticApiModelOutline25.m();
                    TrustedBiddingData.Builder m4 = PACustomAudienceClient$$ExternalSyntheticApiModelOutline21.m();
                    StringBuilder sb2 = new StringBuilder();
                    String str4 = baseUri;
                    if (str4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str4 = null;
                    }
                    sb2.append(str4);
                    sb2.append("?trusted_bidding");
                    Uri parse2 = Uri.parse(sb2.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse2, "Uri.parse(this)");
                    TrustedBiddingData m5 = m4.setTrustedBiddingUri(parse2).setTrustedBiddingKeys(CollectionsKt.listOf("")).build();
                    Intrinsics.checkNotNullExpressionValue(m5, "Builder()\n              …\n                .build()");
                    PACustomAudienceClient$$ExternalSyntheticApiModelOutline26.m();
                    CustomAudience.Builder m6 = PACustomAudienceClient$$ExternalSyntheticApiModelOutline22.m().setName(validateAndCreateCAName).setBuyer(AdTechIdentifier.fromString("facebook.com"));
                    StringBuilder sb3 = new StringBuilder();
                    String str5 = baseUri;
                    if (str5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str5 = null;
                    }
                    sb3.append(str5);
                    sb3.append("?daily&app_id=");
                    sb3.append(str);
                    Uri parse3 = Uri.parse(sb3.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse3, "Uri.parse(this)");
                    CustomAudience.Builder m7 = m6.setDailyUpdateUri(parse3);
                    StringBuilder sb4 = new StringBuilder();
                    String str6 = baseUri;
                    if (str6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("baseUri");
                        str6 = null;
                    }
                    sb4.append(str6);
                    sb4.append("?bidding");
                    Uri parse4 = Uri.parse(sb4.toString());
                    Intrinsics.checkExpressionValueIsNotNull(parse4, "Uri.parse(this)");
                    CustomAudience m8 = m7.setBiddingLogicUri(parse4).setTrustedBiddingData(m5).setUserBiddingSignals(AdSelectionSignals.fromString("{}")).setAds(CollectionsKt.listOf(m3)).build();
                    Intrinsics.checkNotNullExpressionValue(m8, "Builder()\n              …(listOf(dummyAd)).build()");
                    PACustomAudienceClient$$ExternalSyntheticApiModelOutline27.m();
                    JoinCustomAudienceRequest m9 = PACustomAudienceClient$$ExternalSyntheticApiModelOutline23.m().setCustomAudience(m8).build();
                    Intrinsics.checkNotNullExpressionValue(m9, "Builder().setCustomAudience(ca).build()");
                    CustomAudienceManager customAudienceManager2 = customAudienceManager;
                    if (customAudienceManager2 != null) {
                        customAudienceManager2.joinCustomAudience(m9, Executors.newSingleThreadExecutor(), m);
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, "Failed to join Custom Audience: " + e);
                GpsDebugLogger gpsDebugLogger5 = gpsDebugLogger;
                if (gpsDebugLogger5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                    gpsDebugLogger3 = gpsDebugLogger2;
                } else {
                    gpsDebugLogger3 = gpsDebugLogger5;
                }
                Bundle bundle = new Bundle();
                bundle.putString("gps_pa_failed_reason", e.toString());
                Unit unit = Unit.INSTANCE;
                gpsDebugLogger3.log("gps_pa_failed", bundle);
            } catch (Error e2) {
                Log.w(TAG, "Failed to join Custom Audience: " + e2);
                GpsDebugLogger gpsDebugLogger6 = gpsDebugLogger;
                if (gpsDebugLogger6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                    gpsDebugLogger4 = gpsDebugLogger2;
                } else {
                    gpsDebugLogger4 = gpsDebugLogger6;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("gps_pa_failed_reason", e2.toString());
                Unit unit2 = Unit.INSTANCE;
                gpsDebugLogger4.log("gps_pa_failed", bundle2);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final String validateAndCreateCAName(String str, String str2) {
        if (!(CrashShieldHandler.isObjectCrashing(this) || str == null || str2 == null)) {
            try {
                if (!Intrinsics.areEqual((Object) str2, (Object) "_removed_")) {
                    if (!StringsKt.contains$default((CharSequence) str2, (CharSequence) "gps", false, 2, (Object) null)) {
                        return str + '@' + str2 + '@' + (System.currentTimeMillis() / ((long) 1000)) + "@1";
                    }
                }
                return null;
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
        return null;
    }
}
