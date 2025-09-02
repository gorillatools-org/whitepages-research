package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@SafeParcelable.Class(creator = "UploadBatchesCriteriaCreator")
public final class zzpc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzpc> CREATOR = new zzpd();
    @SafeParcelable.Field(id = 1)
    public final List zza;

    @SafeParcelable.Constructor
    zzpc(@SafeParcelable.Param(id = 1) List list) {
        this.zza = list;
    }

    public static zzpc zza(zzmf... zzmfArr) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(zzmfArr[0].zza()));
        return new zzpc(arrayList);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.zza;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIntegerList(parcel, 1, list, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
