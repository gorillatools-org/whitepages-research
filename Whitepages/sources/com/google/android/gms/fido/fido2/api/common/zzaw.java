package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

final class zzaw implements Parcelable.Creator {
    zzaw() {
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        try {
            return zzay.zza(parcel.readString());
        } catch (zzax e) {
            throw new RuntimeException(e);
        }
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzay[i];
    }
}
