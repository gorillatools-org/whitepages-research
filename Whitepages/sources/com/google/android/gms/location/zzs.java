package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@ShowFirstParty
@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", id = 1)
    boolean zza;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", id = 2)
    long zzb;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", id = 3)
    float zzc;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", id = 4)
    long zzd;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", id = 5)
    int zze;

    public zzs() {
        this(true, 50, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzs)) {
            return false;
        }
        zzs zzs = (zzs) obj;
        return this.zza == zzs.zza && this.zzb == zzs.zzb && Float.compare(this.zzc, zzs.zzc) == 0 && this.zzd == zzs.zzd && this.zze == zzs.zze;
    }

    public final int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.zza), Long.valueOf(this.zzb), Float.valueOf(this.zzc), Long.valueOf(this.zzd), Integer.valueOf(this.zze));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DeviceOrientationRequest[mShouldUseMag=");
        sb.append(this.zza);
        sb.append(" mMinimumSamplingPeriodMs=");
        sb.append(this.zzb);
        sb.append(" mSmallestAngleChangeRadians=");
        sb.append(this.zzc);
        long j = this.zzd;
        if (j != Long.MAX_VALUE) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(j - elapsedRealtime);
            sb.append("ms");
        }
        if (this.zze != Integer.MAX_VALUE) {
            sb.append(" num=");
            sb.append(this.zze);
        }
        sb.append(']');
        return sb.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zza);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzc);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.writeInt(parcel, 5, this.zze);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzs(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) int i) {
        this.zza = z;
        this.zzb = j;
        this.zzc = f;
        this.zzd = j2;
        this.zze = i;
    }
}
