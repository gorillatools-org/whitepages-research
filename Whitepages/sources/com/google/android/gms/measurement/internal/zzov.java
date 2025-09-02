package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "TriggerUriParcelCreator")
public final class zzov extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzov> CREATOR = new zzow();
    @SafeParcelable.Field(id = 1)
    public final String zza;
    @SafeParcelable.Field(id = 2)
    public final long zzb;
    @SafeParcelable.Field(id = 3)
    public final int zzc;

    @SafeParcelable.Constructor
    zzov(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) int i) {
        this.zza = str;
        this.zzb = j;
        this.zzc = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, str, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeInt(parcel, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
