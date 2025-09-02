package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@SafeParcelable.Class(creator = "ParcelableGeofenceCreator")
@SafeParcelable.Reserved({1000})
@VisibleForTesting
public final class zzbe extends AbstractSafeParcelable implements Geofence {
    public static final Parcelable.Creator<zzbe> CREATOR = new zzbf();
    @SafeParcelable.Field(getter = "getRequestId", id = 1)
    private final String zza;
    @SafeParcelable.Field(getter = "getExpirationTime", id = 2)
    private final long zzb;
    @SafeParcelable.Field(getter = "getType", id = 3)
    private final short zzc;
    @SafeParcelable.Field(getter = "getLatitude", id = 4)
    private final double zzd;
    @SafeParcelable.Field(getter = "getLongitude", id = 5)
    private final double zze;
    @SafeParcelable.Field(getter = "getRadius", id = 6)
    private final float zzf;
    @SafeParcelable.Field(getter = "getTransitionTypes", id = 7)
    private final int zzg;
    @SafeParcelable.Field(defaultValue = "0", getter = "getNotificationResponsiveness", id = 8)
    private final int zzh;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLoiteringDelay", id = 9)
    private final int zzi;

    @SafeParcelable.Constructor
    public zzbe(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 3) short s, @SafeParcelable.Param(id = 4) double d, @SafeParcelable.Param(id = 5) double d2, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) int i3) {
        if (str == null || str.length() > 100) {
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? "requestId is null or too long: ".concat(valueOf) : new String("requestId is null or too long: "));
        } else if (f <= 0.0f) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("invalid radius: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        } else if (d > 90.0d || d < -90.0d) {
            StringBuilder sb2 = new StringBuilder(42);
            sb2.append("invalid latitude: ");
            sb2.append(d);
            throw new IllegalArgumentException(sb2.toString());
        } else if (d2 > 180.0d || d2 < -180.0d) {
            StringBuilder sb3 = new StringBuilder(43);
            sb3.append("invalid longitude: ");
            sb3.append(d2);
            throw new IllegalArgumentException(sb3.toString());
        } else {
            int i4 = i & 7;
            if (i4 != 0) {
                this.zzc = s;
                this.zza = str;
                this.zzd = d;
                this.zze = d2;
                this.zzf = f;
                this.zzb = j;
                this.zzg = i4;
                this.zzh = i2;
                this.zzi = i3;
                return;
            }
            StringBuilder sb4 = new StringBuilder(46);
            sb4.append("No supported transition specified: ");
            sb4.append(i);
            throw new IllegalArgumentException(sb4.toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzbe) {
            zzbe zzbe = (zzbe) obj;
            return this.zzf == zzbe.zzf && this.zzd == zzbe.zzd && this.zze == zzbe.zze && this.zzc == zzbe.zzc;
        }
    }

    public final String getRequestId() {
        return this.zza;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.zzd);
        long doubleToLongBits2 = Double.doubleToLongBits(this.zze);
        return ((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + Float.floatToIntBits(this.zzf)) * 31) + this.zzc) * 31) + this.zzg;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeShort(parcel, 3, this.zzc);
        SafeParcelWriter.writeDouble(parcel, 4, this.zzd);
        SafeParcelWriter.writeDouble(parcel, 5, this.zze);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzf);
        SafeParcelWriter.writeInt(parcel, 7, this.zzg);
        SafeParcelWriter.writeInt(parcel, 8, this.zzh);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        short s = this.zzc;
        if (s != -1) {
            str = s != 1 ? "UNKNOWN" : "CIRCLE";
        } else {
            str = "INVALID";
        }
        return String.format(locale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[]{str, this.zza.replaceAll("\\p{C}", "?"), Integer.valueOf(this.zzg), Double.valueOf(this.zzd), Double.valueOf(this.zze), Float.valueOf(this.zzf), Integer.valueOf(this.zzh / 1000), Integer.valueOf(this.zzi), Long.valueOf(this.zzb)});
    }
}
