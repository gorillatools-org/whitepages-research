package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "UploadBatchesParcelCreator")
public final class zzpe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpe> CREATOR = new zzpf();
    @SafeParcelable.Field(id = 1)
    public final List zza;

    @SafeParcelable.Constructor
    zzpe(@SafeParcelable.Param(id = 1) List list) {
        this.zza = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, list, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
