package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConditionalUserPropertyParcelCreator")
public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzai> CREATOR = new zzaj();
    @SafeParcelable.Field(id = 2)
    public String zza;
    @SafeParcelable.Field(id = 3)
    public String zzb;
    @SafeParcelable.Field(id = 4)
    public zzqb zzc;
    @SafeParcelable.Field(id = 5)
    public long zzd;
    @SafeParcelable.Field(id = 6)
    public boolean zze;
    @SafeParcelable.Field(id = 7)
    public String zzf;
    @SafeParcelable.Field(id = 8)
    public final zzbh zzg;
    @SafeParcelable.Field(id = 9)
    public long zzh;
    @SafeParcelable.Field(id = 10)
    public zzbh zzi;
    @SafeParcelable.Field(id = 11)
    public final long zzj;
    @SafeParcelable.Field(id = 12)
    public final zzbh zzk;

    zzai(zzai zzai) {
        Preconditions.checkNotNull(zzai);
        this.zza = zzai.zza;
        this.zzb = zzai.zzb;
        this.zzc = zzai.zzc;
        this.zzd = zzai.zzd;
        this.zze = zzai.zze;
        this.zzf = zzai.zzf;
        this.zzg = zzai.zzg;
        this.zzh = zzai.zzh;
        this.zzi = zzai.zzi;
        this.zzj = zzai.zzj;
        this.zzk = zzai.zzk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzc, i, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zze);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzg, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzi, i, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzk, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzai(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) zzqb zzqb, @SafeParcelable.Param(id = 5) long j, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) zzbh zzbh, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) zzbh zzbh2, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = 12) zzbh zzbh3) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = zzqb;
        this.zzd = j;
        this.zze = z;
        this.zzf = str3;
        this.zzg = zzbh;
        this.zzh = j2;
        this.zzi = zzbh2;
        this.zzj = j3;
        this.zzk = zzbh3;
    }
}
