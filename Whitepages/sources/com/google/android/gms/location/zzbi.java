package com.google.android.gms.location;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.internal.location.zzaz;

public abstract class zzbi<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzaz> {
    public zzbi(GoogleApiClient googleApiClient) {
        super((Api<?>) LocationServices.API, googleApiClient);
    }

    @KeepForSdk
    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }
}
