package com.google.android.gms.common.moduleinstall;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ModuleInstallIntentResponseCreator")
public class ModuleInstallIntentResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ModuleInstallIntentResponse> CREATOR = new zab();
    @SafeParcelable.Field(getter = "getPendingIntent", id = 1)
    private final PendingIntent zaa;

    @SafeParcelable.Constructor
    @KeepForSdk
    public ModuleInstallIntentResponse(@SafeParcelable.Param(id = 1) PendingIntent pendingIntent) {
        this.zaa = pendingIntent;
    }

    public PendingIntent getPendingIntent() {
        return this.zaa;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPendingIntent(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
