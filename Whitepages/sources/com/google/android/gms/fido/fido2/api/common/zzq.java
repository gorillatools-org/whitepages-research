package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;

@SafeParcelable.Class(creator = "CableAuthenticationDataCreator")
public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzq> CREATOR = new zzr();
    @SafeParcelable.Field(getter = "getVersion", id = 1)
    private final long zza;
    @SafeParcelable.Field(getter = "getClientEid", id = 2)
    private final byte[] zzb;
    @SafeParcelable.Field(getter = "getAuthenticatorEid", id = 3)
    private final byte[] zzc;
    @SafeParcelable.Field(getter = "getSessionPreKey", id = 4)
    private final byte[] zzd;

    @SafeParcelable.Constructor
    zzq(@SafeParcelable.Param(id = 1) long j, @SafeParcelable.Param(id = 2) byte[] bArr, @SafeParcelable.Param(id = 3) byte[] bArr2, @SafeParcelable.Param(id = 4) byte[] bArr3) {
        this.zza = j;
        this.zzb = (byte[]) Preconditions.checkNotNull(bArr);
        this.zzc = (byte[]) Preconditions.checkNotNull(bArr2);
        this.zzd = (byte[]) Preconditions.checkNotNull(bArr3);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq zzq = (zzq) obj;
        if (this.zza != zzq.zza || !Arrays.equals(this.zzb, zzq.zzb) || !Arrays.equals(this.zzc, zzq.zzc) || !Arrays.equals(this.zzd, zzq.zzd)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), this.zzb, this.zzc, this.zzd);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zza);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeByteArray(parcel, 4, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
