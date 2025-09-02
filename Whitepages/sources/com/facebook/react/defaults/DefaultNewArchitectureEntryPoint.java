package com.facebook.react.defaults;

import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import kotlin.Pair;
import kotlin.TuplesKt;

public final class DefaultNewArchitectureEntryPoint {
    public static final DefaultNewArchitectureEntryPoint INSTANCE = new DefaultNewArchitectureEntryPoint();
    private static boolean privateBridgelessEnabled;
    private static boolean privateConcurrentReactEnabled;
    private static boolean privateFabricEnabled;
    private static boolean privateTurboModulesEnabled;

    public static /* synthetic */ void getBridgelessEnabled$annotations() {
    }

    public static /* synthetic */ void getConcurrentReactEnabled$annotations() {
    }

    public static /* synthetic */ void getFabricEnabled$annotations() {
    }

    public static /* synthetic */ void getTurboModulesEnabled$annotations() {
    }

    public static final void load() {
        load$default(false, false, false, 7, (Object) null);
    }

    public static final void load(boolean z) {
        load$default(z, false, false, 6, (Object) null);
    }

    public static final void load(boolean z, boolean z2) {
        load$default(z, z2, false, 4, (Object) null);
    }

    private DefaultNewArchitectureEntryPoint() {
    }

    public static /* synthetic */ void load$default(boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            z3 = true;
        }
        load(z, z2, z3);
    }

    public static final void load(boolean z, boolean z2, boolean z3) {
        Pair isConfigurationValid = INSTANCE.isConfigurationValid(z, z2, z3);
        boolean booleanValue = ((Boolean) isConfigurationValid.component1()).booleanValue();
        String str = (String) isConfigurationValid.component2();
        if (booleanValue) {
            ReactNativeFeatureFlags.override(new DefaultNewArchitectureEntryPoint$load$1(z3, z2, z));
            privateFabricEnabled = z2;
            privateTurboModulesEnabled = z;
            privateConcurrentReactEnabled = z2;
            privateBridgelessEnabled = z3;
            DefaultSoLoader.Companion.maybeLoadSoLibrary();
            return;
        }
        throw new IllegalStateException(str.toString());
    }

    public static final boolean getFabricEnabled() {
        return privateFabricEnabled;
    }

    public static final boolean getTurboModulesEnabled() {
        return privateTurboModulesEnabled;
    }

    public static final boolean getConcurrentReactEnabled() {
        return privateConcurrentReactEnabled;
    }

    public static final boolean getBridgelessEnabled() {
        return privateBridgelessEnabled;
    }

    @VisibleForTesting
    public final Pair isConfigurationValid(boolean z, boolean z2, boolean z3) {
        if (z2 && !z) {
            return TuplesKt.to(Boolean.FALSE, "fabricEnabled=true requires turboModulesEnabled=true (is now false) - Please update your DefaultNewArchitectureEntryPoint.load() parameters.");
        }
        if (!z3 || (z && z2)) {
            return TuplesKt.to(Boolean.TRUE, "");
        }
        return TuplesKt.to(Boolean.FALSE, "bridgelessEnabled=true requires (turboModulesEnabled=true AND fabricEnabled=true) - Please update your DefaultNewArchitectureEntryPoint.load() parameters.");
    }
}
