package com.facebook.react.config;

import com.facebook.proguard.annotations.DoNotStripAny;

@DoNotStripAny
public final class ReactFeatureFlags {
    public static final ReactFeatureFlags INSTANCE = new ReactFeatureFlags();
    public static boolean dispatchPointerEvents;

    private ReactFeatureFlags() {
    }
}
