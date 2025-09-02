package com.facebook.react.defaults;

import com.facebook.react.internal.featureflags.ReactNativeNewArchitectureFeatureFlagsDefaults;

public final class DefaultNewArchitectureEntryPoint$load$1 extends ReactNativeNewArchitectureFeatureFlagsDefaults {
    final /* synthetic */ boolean $bridgelessEnabled;
    final /* synthetic */ boolean $fabricEnabled;
    final /* synthetic */ boolean $turboModulesEnabled;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DefaultNewArchitectureEntryPoint$load$1(boolean z, boolean z2, boolean z3) {
        super(z);
        this.$bridgelessEnabled = z;
        this.$fabricEnabled = z2;
        this.$turboModulesEnabled = z3;
    }

    public boolean useFabricInterop() {
        return this.$bridgelessEnabled || this.$fabricEnabled;
    }

    public boolean enableFabricRenderer() {
        return this.$bridgelessEnabled || this.$fabricEnabled;
    }

    public boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        return this.$bridgelessEnabled || this.$fabricEnabled;
    }

    public boolean useTurboModules() {
        return this.$bridgelessEnabled || this.$turboModulesEnabled;
    }
}
