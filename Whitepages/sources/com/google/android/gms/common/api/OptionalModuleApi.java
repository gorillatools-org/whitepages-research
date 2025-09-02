package com.google.android.gms.common.api;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface OptionalModuleApi {
    @KeepForSdk
    Feature[] getOptionalFeatures();
}
