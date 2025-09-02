package com.facebook.appevents.integrity;

import android.os.Bundle;
import com.facebook.FacebookSdk;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

public final class ProtectedModeManager {
    public static final ProtectedModeManager INSTANCE = new ProtectedModeManager();
    private static final Lazy defaultStandardParameterNames$delegate = LazyKt.lazy(ProtectedModeManager$defaultStandardParameterNames$2.INSTANCE);
    private static boolean enabled;
    private static HashSet standardParams;

    private ProtectedModeManager() {
    }

    public final HashSet getDefaultStandardParameterNames() {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            return (HashSet) defaultStandardParameterNames$delegate.getValue();
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final void enable() {
        Class<ProtectedModeManager> cls = ProtectedModeManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                enabled = true;
                INSTANCE.loadStandardParams();
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public static final boolean isEnabled() {
        Class<ProtectedModeManager> cls = ProtectedModeManager.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return false;
        }
        try {
            return enabled;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return false;
        }
    }

    private final void loadStandardParams() {
        if (!CrashShieldHandler.isObjectCrashing(this)) {
            try {
                FetchedAppSettings queryAppSettings = FetchedAppSettingsManager.queryAppSettings(FacebookSdk.getApplicationId(), false);
                if (queryAppSettings != null) {
                    HashSet convertJSONArrayToHashSet = convertJSONArrayToHashSet(queryAppSettings.getProtectedModeStandardParamsSetting());
                    if (convertJSONArrayToHashSet == null) {
                        convertJSONArrayToHashSet = getDefaultStandardParameterNames();
                    }
                    standardParams = convertJSONArrayToHashSet;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
    }

    private final HashSet convertJSONArrayToHashSet(JSONArray jSONArray) {
        if (!CrashShieldHandler.isObjectCrashing(this) && jSONArray != null) {
            try {
                if (jSONArray.length() != 0) {
                    HashSet hashSet = new HashSet();
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        String string = jSONArray.getString(i);
                        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.getString(i)");
                        hashSet.add(string);
                    }
                    return hashSet;
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
            }
        }
        return null;
    }

    public static final void processParametersForProtectedMode(Bundle bundle) {
        Class<ProtectedModeManager> cls = ProtectedModeManager.class;
        if (!CrashShieldHandler.isObjectCrashing(cls)) {
            try {
                if (enabled && bundle != null && !bundle.isEmpty()) {
                    if (standardParams != null) {
                        ArrayList<String> arrayList = new ArrayList<>();
                        Set<String> keySet = bundle.keySet();
                        Intrinsics.checkNotNullExpressionValue(keySet, "parameters.keySet()");
                        for (String str : keySet) {
                            HashSet hashSet = standardParams;
                            Intrinsics.checkNotNull(hashSet);
                            if (!hashSet.contains(str)) {
                                Intrinsics.checkNotNullExpressionValue(str, "param");
                                arrayList.add(str);
                            }
                        }
                        for (String remove : arrayList) {
                            bundle.remove(remove);
                        }
                        bundle.putString("pm", "1");
                    }
                }
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, cls);
            }
        }
    }

    public final boolean protectedModeIsApplied(Bundle bundle) {
        if (CrashShieldHandler.isObjectCrashing(this) || bundle == null) {
            return false;
        }
        try {
            if (!bundle.containsKey("pm") || !Intrinsics.areEqual(bundle.get("pm"), (Object) "1")) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return false;
        }
    }
}
