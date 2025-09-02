package com.google.android.play.core.review.internal;

import android.os.Bundle;
import android.os.Parcel;

public abstract class zzg extends zzb implements zzh {
    public zzg() {
        super("com.google.android.play.core.inappreview.protocol.IInAppReviewServiceCallback");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i != 2) {
            return false;
        }
        zzc.zzb(parcel);
        zzb((Bundle) zzc.zza(parcel, Bundle.CREATOR));
        return true;
    }
}
