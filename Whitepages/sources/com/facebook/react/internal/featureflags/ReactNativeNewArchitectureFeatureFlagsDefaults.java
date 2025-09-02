package com.facebook.react.internal.featureflags;

import kotlin.jvm.internal.DefaultConstructorMarker;

public class ReactNativeNewArchitectureFeatureFlagsDefaults extends ReactNativeFeatureFlagsDefaults {
    private final boolean newArchitectureEnabled;

    public ReactNativeNewArchitectureFeatureFlagsDefaults() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ReactNativeNewArchitectureFeatureFlagsDefaults(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z);
    }

    public ReactNativeNewArchitectureFeatureFlagsDefaults(boolean z) {
        this.newArchitectureEnabled = z;
    }

    public boolean enableBridgelessArchitecture() {
        return this.newArchitectureEnabled;
    }

    public boolean enableDeletionOfUnmountedViews() {
        return this.newArchitectureEnabled;
    }

    public boolean enableEventEmitterRetentionDuringGesturesOnAndroid() {
        return this.newArchitectureEnabled;
    }

    public boolean enableFabricRenderer() {
        return this.newArchitectureEnabled;
    }

    public boolean useFabricInterop() {
        return this.newArchitectureEnabled;
    }

    public boolean useNativeViewConfigsInBridgelessMode() {
        return this.newArchitectureEnabled || super.useNativeViewConfigsInBridgelessMode();
    }

    public boolean useTurboModuleInterop() {
        return this.newArchitectureEnabled || super.useTurboModuleInterop();
    }

    public boolean useTurboModules() {
        return this.newArchitectureEnabled;
    }
}
