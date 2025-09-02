package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "GoogleSessionIdExtensionCreator")
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    @SafeParcelable.Field(getter = "getSessionId", id = 1)
    private final long zza;

    @SafeParcelable.Constructor
    public zzab(@SafeParcelable.Param(id = 1) long j) {
        this.zza = ((Long) Preconditions.checkNotNull(Long.valueOf(j))).longValue();
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof zzab) && this.zza == ((zzab) obj).zza) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
