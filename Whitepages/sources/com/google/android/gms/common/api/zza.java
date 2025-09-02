package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;

final class zza implements Parcelable.Creator {
    private static final zza zza = new zza(new zzb());
    private final Parcelable.Creator zzb;

    private zza(Parcelable.Creator creator) {
        this.zzb = creator;
    }

    public static zza zza() {
        return zza;
    }

    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int dataPosition = parcel.dataPosition();
        if (parcel.readInt() == -204102970) {
            return zzb.zza(parcel);
        }
        parcel.setDataPosition(dataPosition - 4);
        return ApiMetadata.getEmptyInstance();
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ApiMetadata[i];
    }
}
