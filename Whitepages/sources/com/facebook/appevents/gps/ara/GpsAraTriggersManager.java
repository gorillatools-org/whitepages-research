package com.facebook.appevents.gps.ara;

import android.adservices.measurement.MeasurementManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.core.os.OutcomeReceiverKt$$ExternalSyntheticApiModelOutline0;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerApi33Ext5Impl$$ExternalSyntheticApiModelOutline0;
import androidx.privacysandbox.ads.adservices.measurement.MeasurementManagerApi33Ext5Impl$$ExternalSyntheticApiModelOutline1;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEvent;
import com.facebook.appevents.gps.GpsDebugLogger;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.salesforce.marketingcloud.config.a;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.json.JSONObject;

public final class GpsAraTriggersManager {
    public static final GpsAraTriggersManager INSTANCE = new GpsAraTriggersManager();
    private static final String TAG;
    private static boolean enabled;
    private static GpsDebugLogger gpsDebugLogger;
    private static String serverUri;

    private GpsAraTriggersManager() {
    }

    public static final /* synthetic */ GpsDebugLogger access$getGpsDebugLogger$p() {
        Class<GpsAraTriggersManager> cls = GpsAraTriggersManager.class;
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
        Class<GpsAraTriggersManager> cls = GpsAraTriggersManager.class;
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

    static {
        String cls = GpsAraTriggersManager.class.toString();
        Intrinsics.checkNotNullExpressionValue(cls, "GpsAraTriggersManager::class.java.toString()");
        TAG = cls;
    }

    public static final void enable() {
        Class<GpsAraTriggersManager> cls = GpsAraTriggersManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                enabled = true;
                gpsDebugLogger = new GpsDebugLogger(FacebookSdk.getApplicationContext());
                serverUri = "https://www." + FacebookSdk.getFacebookDomain() + "/privacy_sandbox/mobile/register/trigger";
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void registerTriggerAsync(String str, AppEvent appEvent) {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(str, "applicationId");
                Intrinsics.checkNotNullParameter(appEvent, "event");
                FacebookSdk.getExecutor().execute(new GpsAraTriggersManager$$ExternalSyntheticLambda0(str, appEvent));
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void registerTriggerAsync$lambda$0(String str, AppEvent appEvent) {
        Class<GpsAraTriggersManager> cls = GpsAraTriggersManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                Intrinsics.checkNotNullParameter(str, "$applicationId");
                Intrinsics.checkNotNullParameter(appEvent, "$event");
                INSTANCE.registerTrigger(str, appEvent);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final void registerTrigger(String str, AppEvent appEvent) {
        GpsDebugLogger gpsDebugLogger2;
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                Intrinsics.checkNotNullParameter(str, "applicationId");
                Intrinsics.checkNotNullParameter(appEvent, "event");
                if (isValidEvent(appEvent) && canRegisterTrigger()) {
                    Context applicationContext = FacebookSdk.getApplicationContext();
                    gpsDebugLogger2 = null;
                    MeasurementManager m = MeasurementManagerApi33Ext5Impl$$ExternalSyntheticApiModelOutline1.m(applicationContext.getSystemService(MeasurementManagerApi33Ext5Impl$$ExternalSyntheticApiModelOutline0.m()));
                    if (m == null) {
                        m = MeasurementManager.get(applicationContext.getApplicationContext());
                    }
                    if (m == null) {
                        Log.w(TAG, "FAILURE_GET_MEASUREMENT_MANAGER");
                        GpsDebugLogger gpsDebugLogger3 = gpsDebugLogger;
                        if (gpsDebugLogger3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                            gpsDebugLogger3 = null;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("gps_ara_failed_reason", "Failed to get measurement manager");
                        Unit unit = Unit.INSTANCE;
                        gpsDebugLogger3.log("gps_ara_failed", bundle);
                        return;
                    }
                    String eventParameters = getEventParameters(appEvent);
                    StringBuilder sb = new StringBuilder();
                    String str2 = serverUri;
                    if (str2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("serverUri");
                        str2 = null;
                    }
                    sb.append(str2);
                    sb.append('?');
                    sb.append("app_id");
                    sb.append('=');
                    sb.append(str);
                    sb.append('&');
                    sb.append(eventParameters);
                    Uri parse = Uri.parse(sb.toString());
                    Intrinsics.checkNotNullExpressionValue(parse, "parse(\"$serverUri?$appId…=$applicationId&$params\")");
                    m.registerTrigger(parse, FacebookSdk.getExecutor(), OutcomeReceiverKt$$ExternalSyntheticApiModelOutline0.m(new GpsAraTriggersManager$registerTrigger$outcomeReceiver$1()));
                }
            } catch (Exception e) {
                Log.w(TAG, "FAILURE_TRIGGER_REGISTRATION_FAILED");
                GpsDebugLogger gpsDebugLogger4 = gpsDebugLogger;
                if (gpsDebugLogger4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                } else {
                    gpsDebugLogger2 = gpsDebugLogger4;
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("gps_ara_failed_reason", e.toString());
                Unit unit2 = Unit.INSTANCE;
                gpsDebugLogger2.log("gps_ara_failed", bundle2);
            } catch (Error e2) {
                Log.w(TAG, "FAILURE_TRIGGER_REGISTRATION_FAILED");
                GpsDebugLogger gpsDebugLogger5 = gpsDebugLogger;
                if (gpsDebugLogger5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
                } else {
                    gpsDebugLogger2 = gpsDebugLogger5;
                }
                Bundle bundle3 = new Bundle();
                bundle3.putString("gps_ara_failed_reason", e2.toString());
                Unit unit3 = Unit.INSTANCE;
                gpsDebugLogger2.log("gps_ara_failed", bundle3);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final boolean canRegisterTrigger() {
        GpsDebugLogger gpsDebugLogger2;
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            if (!enabled) {
                return false;
            }
            gpsDebugLogger2 = null;
            Class.forName("android.adservices.measurement.MeasurementManager");
            return true;
        } catch (Exception e) {
            Log.i(TAG, "FAILURE_NO_MEASUREMENT_MANAGER_CLASS");
            GpsDebugLogger gpsDebugLogger3 = gpsDebugLogger;
            if (gpsDebugLogger3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            } else {
                gpsDebugLogger2 = gpsDebugLogger3;
            }
            Bundle bundle = new Bundle();
            bundle.putString("gps_ara_failed_reason", e.toString());
            Unit unit = Unit.INSTANCE;
            gpsDebugLogger2.log("gps_ara_failed", bundle);
            return false;
        } catch (Error e2) {
            Log.i(TAG, "FAILURE_NO_MEASUREMENT_MANAGER_CLASS");
            GpsDebugLogger gpsDebugLogger4 = gpsDebugLogger;
            if (gpsDebugLogger4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("gpsDebugLogger");
            } else {
                gpsDebugLogger2 = gpsDebugLogger4;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("gps_ara_failed_reason", e2.toString());
            Unit unit2 = Unit.INSTANCE;
            gpsDebugLogger2.log("gps_ara_failed", bundle2);
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }

    private final String getEventParameters(AppEvent appEvent) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            JSONObject jSONObject = appEvent.getJSONObject();
            if (jSONObject == null) {
                return "";
            }
            if (jSONObject.length() == 0) {
                return "";
            }
            Iterator<String> keys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "params.keys()");
            return SequencesKt.joinToString$default(SequencesKt.mapNotNull(SequencesKt.asSequence(keys), new GpsAraTriggersManager$getEventParameters$1(jSONObject)), "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    private final boolean isValidEvent(AppEvent appEvent) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return false;
        }
        try {
            String string = appEvent.getJSONObject().getString("_eventName");
            if (Intrinsics.areEqual((Object) string, (Object) "_removed_")) {
                return false;
            }
            Intrinsics.checkNotNullExpressionValue(string, a.h);
            if (!StringsKt.contains$default((CharSequence) string, (CharSequence) "gps", false, 2, (Object) null)) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
