package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "EventParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzbh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbh> CREATOR = new zzbi();
    @SafeParcelable.Field(id = 2)
    public final String zza;
    @SafeParcelable.Field(id = 3)
    public final zzbf zzb;
    @SafeParcelable.Field(id = 4)
    public final String zzc;
    @SafeParcelable.Field(id = 5)
    public final long zzd;

    zzbh(zzbh zzbh, long j) {
        Preconditions.checkNotNull(zzbh);
        this.zza = zzbh.zza;
        this.zzb = zzbh.zzb;
        this.zzc = zzbh.zzc;
        this.zzd = j;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzb);
        return "origin=" + this.zzc + ",name=" + this.zza + ",params=" + valueOf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzbi.zza(this, parcel, i);
    }

    @SafeParcelable.Constructor
    public zzbh(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) zzbf zzbf, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) long j) {
        this.zza = str;
        this.zzb = zzbf;
        this.zzc = str2;
        this.zzd = j;
    }
}
