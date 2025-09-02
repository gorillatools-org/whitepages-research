package com.google.android.gms.common.wrappers;

import android.content.AttributionSource;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class AttributionSourceWrapper {
    private final AttributionSource zza;

    @KeepForSdk
    public AttributionSourceWrapper(AttributionSource attributionSource) {
        this.zza = attributionSource;
    }

    @KeepForSdk
    public AttributionSource getAttributionSource() {
        return this.zza;
    }
}
