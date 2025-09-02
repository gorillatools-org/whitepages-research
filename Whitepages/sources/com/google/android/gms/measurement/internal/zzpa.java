package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UploadBatchParcelCreator")
public final class zzpa extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpa> CREATOR = new zzpb();
    @SafeParcelable.Field(id = 1)
    public final long zza;
    @SafeParcelable.Field(id = 2)
    public byte[] zzb;
    @SafeParcelable.Field(id = 3)
    public final String zzc;
    @SafeParcelable.Field(id = 4)
    public final Bundle zzd;
    @SafeParcelable.Field(id = 5)
    public final int zze;
    @SafeParcelable.Field(id = 6)
    public final long zzf;
    @SafeParcelable.Field(id = 7)
    public String zzg;

    @SafeParcelable.Constructor
    zzpa(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) Bundle bundle, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) long j2, @SafeParcelable.Param(id = 7) String str2) {
        this.zza = j;
        this.zzb = bArr;
        this.zzc = str;
        this.zzd = bundle;
        this.zze = i;
        this.zzf = j2;
        this.zzg = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        long j = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, j);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeBundle(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.writeLong(parcel, 6, this.zzf);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
