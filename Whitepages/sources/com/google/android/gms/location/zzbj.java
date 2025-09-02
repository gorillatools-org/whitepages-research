package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "LocationSettingsConfigurationCreator")
@SafeParcelable.Reserved({3, 4, 1000})
@Deprecated
public final class zzbj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbj> CREATOR = new zzbk();
    @SafeParcelable.Field(defaultValue = "", getter = "getJustificationText", id = 1)
    private final String zza;
    @SafeParcelable.Field(defaultValue = "", getter = "getExperimentId", id = 2)
    private final String zzb;
    @SafeParcelable.Field(defaultValue = "", getter = "getTitleText", id = 5)
    private final String zzc;

    @SafeParcelable.Constructor
    zzbj(@SafeParcelable.Param(id = 5) String str, @SafeParcelable.Param(id = 1) String str2, @SafeParcelable.Param(id = 2) String str3) {
        this.zzc = str;
        this.zza = str2;
        this.zzb = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
