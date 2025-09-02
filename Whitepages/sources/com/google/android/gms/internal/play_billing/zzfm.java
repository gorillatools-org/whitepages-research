package com.google.android.gms.internal.play_billing;

import sun.misc.Unsafe;

final class zzfm extends zzfo {
    zzfm(Unsafe unsafe) {
        super(unsafe);
    }

    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    public final void zzc(Object obj, long j, boolean z) {
        if (zzfp.zzb) {
            zzfp.zzD(obj, j, r3 ? (byte) 1 : 0);
        } else {
            zzfp.zzE(obj, j, r3 ? (byte) 1 : 0);
        }
    }

    public final void zzd(Object obj, long j, byte b) {
        if (zzfp.zzb) {
            zzfp.zzD(obj, j, b);
        } else {
            zzfp.zzE(obj, j, b);
        }
    }

    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    public final boolean zzg(Object obj, long j) {
        if (zzfp.zzb) {
            return zzfp.zzt(obj, j);
        }
        return zzfp.zzu(obj, j);
    }
}
