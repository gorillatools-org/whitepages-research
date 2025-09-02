package com.facebook.react.internal.featureflags;

public interface ReactNativeFeatureFlagsAccessor extends ReactNativeFeatureFlagsProvider {
    String dangerouslyForceOverride(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider);

    void dangerouslyReset();

    void override(ReactNativeFeatureFlagsProvider reactNativeFeatureFlagsProvider);
}
