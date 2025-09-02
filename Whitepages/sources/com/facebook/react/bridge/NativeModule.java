package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface NativeModule {
    boolean canOverrideExistingModule() {
        return false;
    }

    String getName();

    void initialize();

    void invalidate();

    @Deprecated(forRemoval = true, since = "Use invalidate method instead")
    void onCatalystInstanceDestroy() {
    }
}
