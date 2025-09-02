package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConsentParcelCreator")
public final class zzap extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzap> CREATOR = new zzaq();
    @SafeParcelable.Field(id = 1)
    public final Bundle zza;

    @SafeParcelable.Constructor
    public zzap(@SafeParcelable.Param(id = 1) Bundle bundle) {
        this.zza = bundle;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, bundle, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
