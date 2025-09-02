package com.facebook.react.internal.featureflags;

public final class ReactNativeFeatureFlagsForTests {
    public static final ReactNativeFeatureFlagsForTests INSTANCE = new ReactNativeFeatureFlagsForTests();

    private ReactNativeFeatureFlagsForTests() {
    }

    /* access modifiers changed from: private */
    public static final ReactNativeFeatureFlagsAccessor setUp$lambda$0() {
        return new ReactNativeFeatureFlagsLocalAccessor();
    }

    public final void setUp() {
        ReactNativeFeatureFlags.INSTANCE.setAccessorProvider$ReactAndroid_release(new ReactNativeFeatureFlagsForTests$$ExternalSyntheticLambda0());
    }
}
