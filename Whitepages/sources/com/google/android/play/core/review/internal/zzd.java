package com.google.android.play.core.review.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

public final class zzd extends zza implements zzf {
    zzd(IBinder iBinder) {
        super(iBinder, "com.google.android.play.core.inappreview.protocol.IInAppReviewService");
    }

    public final void zzc(String str, Bundle bundle, zzh zzh) {
        Parcel zza = zza();
        zza.writeString(str);
        zzc.zzc(zza, bundle);
        zzc.zzd(zza, zzh);
        zzb(2, zza);
    }
}
