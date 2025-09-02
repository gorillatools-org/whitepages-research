package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "FidoAppIdExtensionCreator")
@SafeParcelable.Reserved({1})
public class FidoAppIdExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator<FidoAppIdExtension> CREATOR = new zzx();
    @SafeParcelable.Field(getter = "getAppId", id = 2)
    private final String zza;

    @SafeParcelable.Constructor
    public FidoAppIdExtension(@SafeParcelable.Param(id = 2) String str) {
        this.zza = (String) Preconditions.checkNotNull(str);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FidoAppIdExtension)) {
            return false;
        }
        return this.zza.equals(((FidoAppIdExtension) obj).zza);
    }

    public String getAppId() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(this.zza);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAppId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
