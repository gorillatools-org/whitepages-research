package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@KeepForSdk
@SafeParcelable.Class(creator = "TelemetryDataCreator")
public class TelemetryData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TelemetryData> CREATOR = new zaab();
    @SafeParcelable.Field(getter = "getTelemetryConfigVersion", id = 1)
    private final int zaa;
    @SafeParcelable.Field(getter = "getMethodInvocations", id = 2)
    private List zab;

    @SafeParcelable.Constructor
    public TelemetryData(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) List list) {
        this.zaa = i;
        this.zab = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zaa);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zab, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final int zaa() {
        return this.zaa;
    }

    public final List zab() {
        return this.zab;
    }

    public final void zac(MethodInvocation methodInvocation) {
        if (this.zab == null) {
            this.zab = new ArrayList();
        }
        this.zab.add(methodInvocation);
    }
}
