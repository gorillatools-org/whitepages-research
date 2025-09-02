package com.google.android.gms.internal.auth;

import sun.misc.Unsafe;

final class zzhh extends zzhi {
    zzhh(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzhj.zza) {
            zzhj.zzi(obj, j, z);
        } else {
            zzhj.zzj(obj, j, z);
        }
    }

    public final void zzd(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    public final void zze(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzf(Object obj, long j) {
        if (zzhj.zza) {
            return zzhj.zzq(obj, j);
        }
        return zzhj.zzr(obj, j);
    }
}
