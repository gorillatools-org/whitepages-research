package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "UserAttributeParcelCreator")
public final class zzqb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzqb> CREATOR = new zzqc();
    @SafeParcelable.Field(id = 1)
    public final int zza;
    @SafeParcelable.Field(id = 2)
    public final String zzb;
    @SafeParcelable.Field(id = 3)
    public final long zzc;
    @SafeParcelable.Field(id = 4)
    public final Long zzd;
    @SafeParcelable.Field(id = 6)
    public final String zze;
    @SafeParcelable.Field(id = 7)
    public final String zzf;
    @SafeParcelable.Field(id = 8)
    public final Double zzg;

    @SafeParcelable.Constructor
    zzqb(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) Long l, @SafeParcelable.Param(id = 5) Float f, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) Double d) {
        this.zza = i;
        this.zzb = str;
        this.zzc = j;
        this.zzd = l;
        this.zzg = i == 1 ? f != null ? Double.valueOf(f.doubleValue()) : null : d;
        this.zze = str2;
        this.zzf = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzqc.zza(this, parcel, i);
    }

    public final Object zza() {
        Long l = this.zzd;
        if (l != null) {
            return l;
        }
        Double d = this.zzg;
        if (d != null) {
            return d;
        }
        String str = this.zze;
        if (str != null) {
            return str;
        }
        return null;
    }

    zzqb(zzqd zzqd) {
        this(zzqd.zzc, zzqd.zzd, zzqd.zze, zzqd.zzb);
    }

    zzqb(String str, long j, Object obj, String str2) {
        Preconditions.checkNotEmpty(str);
        this.zza = 2;
        this.zzb = str;
        this.zzc = j;
        this.zzf = str2;
        if (obj == null) {
            this.zzd = null;
            this.zzg = null;
            this.zze = null;
        } else if (obj instanceof Long) {
            this.zzd = (Long) obj;
            this.zzg = null;
            this.zze = null;
        } else if (obj instanceof String) {
            this.zzd = null;
            this.zzg = null;
            this.zze = (String) obj;
        } else if (obj instanceof Double) {
            this.zzd = null;
            this.zzg = (Double) obj;
            this.zze = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }
}
