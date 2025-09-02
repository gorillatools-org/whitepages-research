package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleSilentVerificationExtensionCreator")
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    @SafeParcelable.Field(getter = "getSilentVerification", id = 1)
    private final boolean zza;

    @SafeParcelable.Constructor
    public zzad(@SafeParcelable.Param(id = 1) boolean z) {
        this.zza = ((Boolean) Preconditions.checkNotNull(Boolean.valueOf(z))).booleanValue();
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof zzad) && this.zza == ((zzad) obj).zza) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
