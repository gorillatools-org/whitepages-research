package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class HashAccumulator {
    private int zaa = 1;

    @KeepForSdk
    public HashAccumulator addObject(Object obj) {
        this.zaa = (this.zaa * 31) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.zaa;
    }

    public final HashAccumulator zaa(boolean z) {
        this.zaa = (this.zaa * 31) + (z ? 1 : 0);
        return this;
    }
}
